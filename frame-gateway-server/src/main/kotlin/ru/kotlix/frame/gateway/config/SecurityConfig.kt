package ru.kotlix.frame.gateway.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import ru.kotlix.frame.auth.api.token.TokenDecoder
import ru.kotlix.frame.auth.token.JWSTokenDecoder
import ru.kotlix.frame.gateway.security.TokenAuthenticationExceptionHandler
import ru.kotlix.frame.gateway.security.TokenAuthenticationProvider
import ru.kotlix.frame.gateway.service.AuthenticationService
import ru.kotlix.frame.parties.server.security.TokenAuthenticationFilter
import ru.kotlix.frame.auth.api.token.dto.UserInfo as AuthUserInfo

@Configuration
class SecurityConfig {
    @Bean
    fun tokenDecoder(objectMapper: ObjectMapper): TokenDecoder<AuthUserInfo> = JWSTokenDecoder(objectMapper)

    @Bean
    fun authenticationProvider(
        authenticationService: AuthenticationService,
        tokenDecoder: TokenDecoder<AuthUserInfo>,
    ): AuthenticationProvider = TokenAuthenticationProvider(authenticationService, tokenDecoder)

    @Bean
    fun authenticationManager(authenticationProvider: AuthenticationProvider): AuthenticationManager =
        ProviderManager(authenticationProvider)

    @Bean
    fun filter(authenticationManager: AuthenticationManager) =
        TokenAuthenticationFilter(AntPathRequestMatcher("/api/v1/**"), authenticationManager)

    @Bean
    fun filterChain(
        http: HttpSecurity,
        authenticationProvider: TokenAuthenticationProvider,
        authenticationFilter: TokenAuthenticationFilter,
    ): SecurityFilterChain =
        http
            .cors { it.disable() }
            .csrf { it.disable() }
            .formLogin { it.disable() }
            .logout { it.disable() }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(authenticationFilter, AnonymousAuthenticationFilter::class.java).authorizeHttpRequests {
                it.requestMatchers("/api/v1/**").authenticated()
            }
            .exceptionHandling {
                it.authenticationEntryPoint(TokenAuthenticationExceptionHandler())
            }
            .build()
}

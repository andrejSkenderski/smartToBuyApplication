package com.finki.smartToBuyApp.security

import com.finki.smartToBuyApp.filters.JwtRequestFilter
import com.finki.smartToBuyApp.service.MyUserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
class WebSecurityConfig(
    val authService: MyUserService,
    val jwtRequestFilter: JwtRequestFilter

) : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(authService).passwordEncoder(getPasswordEncoder())
    }

    override fun configure(http: HttpSecurity) {
        http.cors().and().csrf().disable().authorizeRequests()
            .antMatchers("**").permitAll()
            .antMatchers("/api/**").permitAll()
//            .antMatchers("/api/tables/**").hasAnyAuthority("WAITER", "MANAGER")
//            .antMatchers("/api/drinks/**").hasAnyAuthority("WAITER", "MANAGER")
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

//        http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter::class.java)
    }

    @Bean
    fun getPasswordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder(10)
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

}
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
class WebSecurityConfig : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder) {
//        auth.userDetailsService(authService).passwordEncoder(passwordEncoder.passwordEncoder())
    }

    override fun configure(http: HttpSecurity) {
        http.cors().and().csrf().disable().authorizeRequests()
            .antMatchers("/api/**").permitAll()
//            .antMatchers("/api/tables/**").hasAnyAuthority("WAITER", "MANAGER")
//            .antMatchers("/api/drinks/**").hasAnyAuthority("WAITER", "MANAGER")
//            .antMatchers("/api/locales/**").hasAuthority("MANAGER")
//            .antMatchers("/api/analytics/**").hasAuthority("MANAGER")
//            .antMatchers("/api/storage/**").hasAuthority("MANAGER")
//            .antMatchers("/api/employees/**").hasAuthority("MANAGER")
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

//        http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter::class.java)
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }
}
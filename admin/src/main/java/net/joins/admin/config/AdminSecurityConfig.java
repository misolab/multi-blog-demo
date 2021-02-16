package net.joins.admin.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;
import net.joins.admin.security.AdminAuthenticationProvider;
import net.joins.webapp.config.WebSecurityConfig;
import net.joins.webapp.security.AuthenticationErrorHandler;
import net.joins.webapp.security.JwtAuthenticationFilter;


@RequiredArgsConstructor
@EnableWebSecurity//(debug = true)
public class AdminSecurityConfig extends WebSecurityConfig {

    final AdminAuthenticationProvider authenticationProvider;

    JwtAuthenticationFilter authenticationFilter() {
        return new JwtAuthenticationFilter(authenticationProvider);
    }

    public AuthenticationEntryPoint authenticationErrorHandler() {
        return new AuthenticationErrorHandler();
    }

    String[] permitUrl = { "/", "/hello" , "/api/user/ip", "/api/user/login", "/api/user/logout" };

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.ignoring().antMatchers("/manifest.json", "/img/**", "/**/*.woff", "/**/*.ttf", "/**/*.ico", "/**/*.css", "/**/*.js", "/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //  for h2 console
        http
            .csrf().disable()
            .headers().frameOptions().disable()
        .and()
            .authorizeRequests()
                .antMatchers("/h2/**").permitAll();
        http
            .csrf().disable()
            .httpBasic().disable()
            .authorizeRequests()
                .antMatchers(permitUrl).permitAll()
                .anyRequest().authenticated();

        //  for session stateless
        http
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
                .addFilterAt(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
         .exceptionHandling()
             .authenticationEntryPoint(authenticationErrorHandler());
    }
}


package net.joins.admin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.joins.common.jwt.JwtUtil;
import net.joins.common.util.DateTimeUtils;
import net.joins.common.util.LDAPAuthenticator;


@Configuration
@ConfigurationProperties(prefix="app")
public class AppConfig {

    @Bean
    public JwtUtil jwtUtil(@Value("${app.jwt.secret}") String secret, @Value("${app.jwt.limit}") String limit) {
        long milli = DateTimeUtils.parse(limit);
        JwtUtil jwtUtil = new JwtUtil(secret, milli);
        return jwtUtil;
    }

    @Bean
    public LDAPAuthenticator ldap(@Value("${app.ldap.principal}") String principal, @Value("${app.ldap.providerUrl}") String providerUrl) {
        LDAPAuthenticator ldap = new LDAPAuthenticator(principal, providerUrl);
        return ldap;
    }
}



package net.vanderkast.wishlists.server.config;

import lombok.AllArgsConstructor;
import net.vanderkast.wishlists.server.auth.BasicAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@ConfigurationProperties(prefix = "security")
@EnableWebSecurity
@AllArgsConstructor(onConstructor_ = @Autowired)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final BasicAuthenticationProvider authenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security.cors()
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/auth/**", "/api/v1/users/signup").anonymous()
                .antMatchers("/api/v1/**").authenticated()
                .and().httpBasic();
        security.csrf().disable();
        security.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}

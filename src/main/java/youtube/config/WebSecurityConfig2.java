package youtube.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import youtube.security.RESTAuthEntryPoint;
import youtube.security.RESTAuthFailureHandler;
import youtube.security.RESTAuthSuccessHandler;

//@Configuration
//@EnableWebSecurity
public class WebSecurityConfig2 extends WebSecurityConfigurerAdapter{


    @Autowired
    private RESTAuthEntryPoint authenticationEntryPoint;
    @Autowired
    private RESTAuthFailureHandler authenticationFailureHandler;
    @Autowired
    private RESTAuthSuccessHandler authenticationSuccessHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .inMemoryAuthentication()
                .withUser("user").password("user").roles("USER")
                .and()
                .withUser("admin").password("admin").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/api/**").authenticated();
        http.csrf().disable();
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
        http.formLogin().successHandler(authenticationSuccessHandler);
        http.formLogin().failureHandler(authenticationFailureHandler);
    }
}

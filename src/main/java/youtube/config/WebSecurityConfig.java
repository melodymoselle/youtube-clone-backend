package youtube.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.SessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
@EnableSpringHttpSession
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        String[] patterns = new String[] {
                "/",
                "/register",
                "/login",
                "/users/**",
                "/videos/**"
        };

        http
                .authorizeRequests()
                .antMatchers(patterns)
                .permitAll()
                .antMatchers("/upload")
                .hasRole("USER")
                .and()
                .csrf()
                .disable()
                .httpBasic();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "SELECT * FROM users WHERE id = ?")
                .authoritiesByUsernameQuery(
                        "select username, 'ROLE_USER' from Spitter where username=?")
                .passwordEncoder(new StandardPasswordEncoder("53cr3t"));
    }

    @Bean
    public SessionRepository sessionRepository() {
        return new MapSessionRepository();
    }

    @Bean
    public HeaderHttpSessionStrategy sessionStrategy() {
        return new HeaderHttpSessionStrategy();
    }
}
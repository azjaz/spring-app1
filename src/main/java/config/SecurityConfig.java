package config;

import data.SpitterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import security.SpitterUserService;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/spitters/**").hasAuthority("ROLE_SPITTER")
                .antMatchers(HttpMethod.POST, "/spitters")
                .access("hasRole('ROLE_SPITTER')")
                .anyRequest().permitAll();

    }

//    user configuration with in-memory db
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("user").password("pass").authorities("ROLE_USER")
//                .and()
//                .withUser("admin").password("admin").roles("USER", "ADMIN");
//    }
//    user configuration with jdbc db
//    @Override
//    public void configure(AuthenticationManagerBuilder authJdbc) throws Exception {
//        authJdbc
//                .jdbcAuthentication()
//                .dataSource(dataSource)
//                .usersByUsernameQuery("selct username, password, true "
//                + "from Spittr where username=?")
//                .authoritiesByUsernameQuery("select username, 'ROLE_USER' from Spitter where username=?")
//        .passwordEncoder(new BCryptPasswordEncoder(Integer.parseInt("a12")));
//    }
//    user configuration with ldap db
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .ldapAuthentication()
//                .userSearchBase("ou=people")
//                .userSearchFilter("(uid={0})")
//                .groupSearchBase("ou=groups")
//                .groupSearchFilter("member={0}")
//                .contextSource()
//                .root("dc=habuma, dc=com")
//                .ldif("classpath:users.ldif");
//    }
    @Autowired
    SpitterRepository spitterRepository;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new SpitterUserService(spitterRepository));
    }
}

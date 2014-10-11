package com.hoserdude.toboot.config;

import com.hoserdude.toboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties security;

    @Autowired
    private UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest().fullyAuthenticated();

        http.csrf().disable();

        // If we weren't using OpenID
//        http.formLogin()
//                .loginPage("/login")
//                .failureUrl("/login?error")
//                .defaultSuccessUrl("/home")
//                .permitAll()
//              .and()
//                .logout()
//                    .logoutUrl("/logout").permitAll()
//                    .logoutSuccessUrl("/");
        http.openidLogin()
                .loginPage("/openId")
                .defaultSuccessUrl("/home")
                .permitAll()
                .authenticationUserDetailsService(userService)
                .attributeExchange("https://www.google.com/.*")
                .attribute("email")
                .type("http://axschema.org/contact/email")
                .required(true)
                .and()
                .attribute("firstname")
                .type("http://axschema.org/namePerson/first")
                .required(true)
                .and()
                .attribute("lastname")
                .type("http://axschema.org/namePerson/last")
                .required(true)
                .and()
                .and()
                .attributeExchange(".*yahoo.com.*")
                .attribute("email")
                .type("http://axschema.org/contact/email")
                .required(true)
                .and()
                .attribute("fullname")
                .type("http://axschema.org/namePerson")
                .required(true)
                .and()
                .and()
                .attributeExchange(".*myopenid.com.*")
                .attribute("email")
                .type("http://schema.openid.net/contact/email")
                .required(true)
                .and()
                .attribute("fullname")
                .type("http://schema.openid.net/namePerson")
                .required(true);

        http.logout()
                .logoutUrl("/logout").permitAll()
                .logoutSuccessUrl("/");
    }

//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("admin").password("admin").roles("ADMIN", "USER")
//                .and()
//                .withUser("user").password("user").roles("USER");
//    }

}

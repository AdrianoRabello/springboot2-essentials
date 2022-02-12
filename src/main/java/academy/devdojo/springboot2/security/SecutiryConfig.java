package academy.devdojo.springboot2.security;

import academy.devdojo.springboot2.service.DevDojoUserDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @autor adriano rabello 15/07/2021 2:56 PM
 **/
@EnableWebSecurity
@Slf4j
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecutiryConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DevDojoUserDetailService devDojoUserDetailService;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/animes/admin/**").hasAnyRole("ADMIN")
                .antMatchers("/animes").hasAnyRole("USER")
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        log.info("log info {} " , passwordEncoder.encode("123456"));
        auth.userDetailsService(devDojoUserDetailService).passwordEncoder(passwordEncoder);
        auth.inMemoryAuthentication()
                .withUser("adriano.rabello")
                .password(passwordEncoder.encode("123456"))
                .roles("USER");


        super.configure(auth);


    }
}

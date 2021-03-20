package com.example.demo.config;

import com.example.demo.model.Product;
import com.example.demo.service.LoginUser.ILoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ILoginUserService loginUserService;

    @Autowired
    private ProductSuccessHandle productSuccessHandle;

    //create username and password to login
//    @Bean
//    public UserDetailsService userDetailsService() {
//        User.UserBuilder userBuilder = User.withDefaultPasswordEncoder();
//        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
//        inMemoryUserDetailsManager.createUser(userBuilder.username("thinh").password("1010").roles("ADMIN").build());
//        inMemoryUserDetailsManager.createUser(userBuilder.username("customer").password("1010").roles("USER").build());
//        return inMemoryUserDetailsManager;
//    }


    //lay du lieu user tu trong DB
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService((UserDetailsService) loginUserService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
//phan quyen theo tung tai khoan
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/list").permitAll()
                .and()
                .authorizeRequests().antMatchers("/create").hasAnyRole("ADMIN", "USER")
                .and()
                .authorizeRequests().antMatchers("/delete/**").hasRole("ADMIN")
                .and()
                .authorizeRequests().antMatchers("/update/**").hasRole("USER")
                .and()
                .formLogin()
                .and()
                .formLogin().successHandler(productSuccessHandle)
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .and()
                .exceptionHandling().accessDeniedPage("/khongcoquyen");
        http.csrf().disable();
    }
}

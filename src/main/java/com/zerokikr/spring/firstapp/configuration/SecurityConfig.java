package com.zerokikr.spring.firstapp.configuration;

import com.zerokikr.spring.firstapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

   // private DataSource dataSource;  //  стандартный Spring Bean, отвечающий за подключение к базе данных (БД)
                                      //  настройки DataSource находятся в файле application.properties
   /* @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }*/

    private UserService userService;  //  наследник UserDetailsService. Необходим для работы Spring Security.

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

   /* @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {  //  данные для аутентификации берутся из базы данных
        auth.jdbcAuthentication().dataSource(dataSource);  //  для работы данной (стандартной) конфигурации необходимо подключение зависимости spring-boot-starter-jdbc в файле pom.xml
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/products/**").hasAnyRole("ADMIN")
                .anyRequest().permitAll()
                .and()
                .formLogin()      //  будет показана стандартная для Spring форма для логина
                //                .loginPage("/login")   //  можно также указать запрос страницы с формой логина
                //                .loginProcessingUrl("/authenticateTheUser")       //  необходимо будет указать куда форма пошлет POST-запрос после того как юзер заполнит ее
                .permitAll();
    }

   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws  Exception {
       auth.authenticationProvider(authenticationProvider());
   }

   @Bean
   public BCryptPasswordEncoder passwordEncoder () {
       return new BCryptPasswordEncoder();
   }

   @Bean // для аутентификации пользователей используем написанный DAOAuthenticationProvider вместо стандартного
   public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
   }






}

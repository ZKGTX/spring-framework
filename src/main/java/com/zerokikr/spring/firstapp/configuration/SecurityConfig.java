package com.zerokikr.spring.firstapp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private DataSource dataSource;  //  стандартный Spring Bean, отвечающий за подключение к базе данных (БД)
                                    //  настройки DataSource находятся в файле application.properties
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {  //  данные для аутентификации берутся из базы данных
        auth.jdbcAuthentication().dataSource(dataSource);  //  для работы данной конфигурации необходимо подключение зависимости spring-boot-starter-jdbc в файле pom.xml
    }

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




}

package com.example.veresk_shop.config;

import com.example.veresk_shop.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig{
    private final PersonDetailsService personDetailsService;

    @Bean
    public PasswordEncoder getPasswordEncode(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests() //защищены аутентификацией
                .requestMatchers("/admin").hasRole("ADMIN") //admin -  роль ADMIN

                //"/product/info/{id}"

                .requestMatchers("/authentication", "/registration", "/error", "/resources/**", "/static/**", "/css/**", "/js/**", "/img/**","/product", "/product/info/{id}", "/product/search","/index").permitAll()
                .requestMatchers("/admin", "/product/add").hasRole("ADMIN")
                // либо через preAutorize yад классом
                .anyRequest().hasAnyRole("USER", "ADMIN")
                .and() // аутентификация + настройка доступа
                .formLogin().loginPage("/authentication") // url защищенных стр.
                .loginProcessingUrl("/process_login") // данные с формы. (url для обработки формы аутентификации по средствам Spring Security.SS=сверка с БД
                .defaultSuccessUrl("/user/personAccount", true) //  url  после успешной аутентификации
                .failureUrl("/authentication?error") // проваленная аутентификации.сообщение "Неправильный логин или пароль"
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/product");
        return http.build();
    }

    @Autowired
    public SecurityConfig(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }



    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(personDetailsService)
                .passwordEncoder(getPasswordEncode());
    }

}

package com.cengiz.natsjava.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.StaticHeadersWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class WebSecurityConfig {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  private final JwtLogoutSuccessHandler jwtLogoutSuccessHandler;


  @Bean
  protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer.xssProtection(Customizer.withDefaults())
            .addHeaderWriter(new StaticHeadersWriter("X-Content-Security-Policy", "script-src 'self'")))
        .cors(Customizer.withDefaults())
        .csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .exceptionHandling(exp -> {
          exp.accessDeniedHandler(new JwtHttp403RestAccessDeniedHandler());
          exp.authenticationEntryPoint(new JwtAuthenticationEntryPoint());
        })
        .logout(logout -> {
          logout.logoutSuccessHandler(jwtLogoutSuccessHandler);
          logout.logoutRequestMatcher(new AntPathRequestMatcher(SecurityConstant.LOGOUT));
        })
        .authorizeHttpRequests(req -> {
          req.requestMatchers(SecurityConstant.VATANDAS_LOGIN,SecurityConstant.MESAJ)
              .permitAll();
          req.anyRequest().authenticated();
        })
        .formLogin(AbstractHttpConfigurer::disable)
        .httpBasic(AbstractHttpConfigurer::disable);
    http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.addAllowedOrigin("http://localhost:8084,http://localhost:8086");  //,http://localhost:8084,http://localhost:8086
    configuration.addAllowedMethod("*");
    configuration.addAllowedHeader("*");
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  @Bean(BeanIds.AUTHENTICATION_MANAGER)
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }
}


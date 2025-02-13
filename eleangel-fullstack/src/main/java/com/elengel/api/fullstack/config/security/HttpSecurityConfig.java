package com.elengel.api.fullstack.config.security;

import com.elengel.api.fullstack.config.security.filter.JwtAuthenticationFilter;
import com.elengel.api.fullstack.persistence.util.RoleEnum;
import com.elengel.api.fullstack.persistence.util.RolePermissionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class HttpSecurityConfig {

    @Autowired
    private AuthenticationProvider daoAuthProvider;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;


    @Autowired
    private AuthorizationManager<RequestAuthorizationContext> authorizationManager;

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        SecurityFilterChain security= http
                //.cors(Customizer.withDefaults())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf( csrfconfig -> csrfconfig.disable())
                .sessionManagement(sessMagConfig->sessMagConfig.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(daoAuthProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authReqConfig->{

                    authReqConfig.anyRequest().access(authorizationManager);

                  //buildRequestMatcherporControlador(authReqConfig);

                })
                .build();
        return security;


    }

    //nuevo por react.
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173")); // Permite solicitudes desde este origen
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Métodos permitidos
        configuration.setAllowedHeaders(Arrays.asList("*")); // Permite todos los encabezados
        configuration.setAllowCredentials(true); // Permite credenciales (cookies, headers de autenticación)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Aplica la configuración a todas las rutas
        return source;
    }

    private static void buildRequestMatcherporControlador(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authReqConfig) {

        /*PUBLICOS ENDPOINTS*/
        //Ant
        authReqConfig.requestMatchers(HttpMethod.POST,"/customers").permitAll();
        authReqConfig.requestMatchers(HttpMethod.POST,"/auth/authenticate").permitAll();
        authReqConfig.requestMatchers(HttpMethod.GET,"/auth/validate-token").permitAll();


        authReqConfig.anyRequest().authenticated();
    }


    private static void buildRequestMatcherhasRole(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authReqConfig) {
        //peticiones basadas en coincidencias http
        /*PRODUCTS*/
        authReqConfig.requestMatchers(HttpMethod.GET,"/products").hasAnyRole(RoleEnum.ADMINISTRATOR.name(), RoleEnum.ASSITANT.name());
        authReqConfig.requestMatchers(HttpMethod.GET,"/products/{productId}").hasAnyRole(RoleEnum.ADMINISTRATOR.name(), RoleEnum.ASSITANT.name());
        authReqConfig.requestMatchers(HttpMethod.POST,"/products").hasRole(RoleEnum.ADMINISTRATOR.name());
        authReqConfig.requestMatchers(HttpMethod.PUT,"/products/{productId}").hasAnyRole(RoleEnum.ADMINISTRATOR.name(), RoleEnum.ASSITANT.name());
        authReqConfig.requestMatchers(HttpMethod.PUT,"/products/{productId}/disabled").hasRole(RoleEnum.ADMINISTRATOR.name());


        /*CATEGORIES*/
        authReqConfig.requestMatchers(HttpMethod.GET,"/categories").hasAnyRole(RoleEnum.ADMINISTRATOR.name(), RoleEnum.ASSITANT.name());
        authReqConfig.requestMatchers(HttpMethod.GET,"/categories/{categoryId}").hasAnyRole(RoleEnum.ADMINISTRATOR.name(), RoleEnum.ASSITANT.name());
        authReqConfig.requestMatchers(HttpMethod.POST,"/categories").hasRole(RoleEnum.ADMINISTRATOR.name());
        authReqConfig.requestMatchers(HttpMethod.PUT,"/categories/{productId}").hasAnyRole(RoleEnum.ADMINISTRATOR.name(), RoleEnum.ASSITANT.name());
        authReqConfig.requestMatchers(HttpMethod.PUT,"/categories/{categoryId}/disabled").hasRole(RoleEnum.ADMINISTRATOR.name());

        /*PROFILE*/
        authReqConfig.requestMatchers(HttpMethod.GET,"/auth/profile").hasAnyRole(RoleEnum.ADMINISTRATOR.name(), RoleEnum.ASSITANT.name(), RoleEnum.CUSTOMER.name(), RoleEnum.GUARDIA.name());

        /*PUBLICOS ENDPOINTS*/
        //Ant
        authReqConfig.requestMatchers(HttpMethod.POST,"/customers").permitAll();
        authReqConfig.requestMatchers(HttpMethod.POST,"/auth/authenticate").permitAll();
        authReqConfig.requestMatchers(HttpMethod.GET,"/auth/validate-token").permitAll();


        authReqConfig.anyRequest().authenticated();
    }


    private static void buildRequestMatcherporAuthority(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry authReqConfig) {
        //peticiones basadas en coincidencias http
        /*PRODUCTS*/
        authReqConfig.requestMatchers(HttpMethod.GET,"/products").hasAuthority(RolePermissionEnum.READ_ALL_PRODUCTS.name());
        authReqConfig.requestMatchers(HttpMethod.GET,"/products/{productId}").hasAuthority(RolePermissionEnum.READ_ONE_PRODUCT.name());
        authReqConfig.requestMatchers(HttpMethod.POST,"/products").hasAuthority(RolePermissionEnum.CREATE_ONE_PRODUCT.name());
        authReqConfig.requestMatchers(HttpMethod.PUT,"/products/{productId}").hasAuthority(RolePermissionEnum.UPDATE_ONE_PRODUCT.name());
        authReqConfig.requestMatchers(HttpMethod.PUT,"/products/{productId}/disabled").hasAuthority(RolePermissionEnum.DISABLE_ONE_PRODUCT.name());


        /*CATEGORIES*/
        authReqConfig.requestMatchers(HttpMethod.GET,"/categories").hasAuthority(RolePermissionEnum.READ_ALL_CATEGORIES.name());
        authReqConfig.requestMatchers(HttpMethod.GET,"/categories/{categoryId}").hasAuthority(RolePermissionEnum.READ_ONE_CATEGORY.name());
        authReqConfig.requestMatchers(HttpMethod.POST,"/categories").hasAuthority(RolePermissionEnum.CREATE_ONE_CATEGORY.name());
        authReqConfig.requestMatchers(HttpMethod.PUT,"/categories/{productId}").hasAuthority(RolePermissionEnum.UPDATE_ONE_CATEGORY.name());
        authReqConfig.requestMatchers(HttpMethod.PUT,"/categories/{categoryId}/disabled").hasAuthority(RolePermissionEnum.DISABLE_ONE_CATEGORY.name());

        /*PROFILE*/
        authReqConfig.requestMatchers(HttpMethod.GET,"/auth/profile").hasAuthority(RolePermissionEnum.READ_MY_PROFILE.name());

        /*PUBLICOS ENDPOINTS*/
        //Ant
        authReqConfig.requestMatchers(HttpMethod.POST,"/customers").permitAll();
        authReqConfig.requestMatchers(HttpMethod.POST,"/auth/authenticate").permitAll();
        authReqConfig.requestMatchers(HttpMethod.GET,"/auth/validate-token").permitAll();


        authReqConfig.anyRequest().authenticated();
    }


}

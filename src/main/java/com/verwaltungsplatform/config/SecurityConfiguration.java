package com.verwaltungsplatform.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.google.common.collect.ImmutableList;
import com.verwaltungsplatform.service.UserServiceImpl;
import com.verwaltungsplatform.util.JwtFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	private UserServiceImpl userServiceImpl;
    
	@Autowired
    private JwtFilter jwtFilter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userServiceImpl);
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
        		.antMatchers(
					"/login",
					"/registration",
					"/lernender/wochenplan/{studentId}",
					"/lehrender/wochenplan/{teacherId}",
					"/sekretariat/wochenplan/klasse/{classId}",
					"/sekretariat/wochenplan/{teacherId}",
					"/eltern/wochenplan/{studentId}",
					"/sekretariat/wochenplan/neuestunde",
					"/sekretariat/wochenplan/stunde/{lessonId}",
					"/sekretariat/wochenplan/stunde/{lessonId}/termin",
					"/sekretariat/wochenplan/stunde/{lessonId}/klasse",
					"/sekretariat/wochenplan/stunde/{lessonId}/lehrender",
					"/sekretariat/wochenplan/stunde/{lessonId}/fach",
					"/sekretariat/wochenplan/stunde/edit/{lessonId}",
					"/sekretariat/wochenplan/stunde/{lessonId}/loeschen",
					"/restorePassword",
					"/restorePassword/code",
					"/restorePassword/changePassword",
					"/sekretariat/changerole",
					"/admin/changerole",
					"/lernender/ankuendigungen",
					"/lehrender/ankuendigungen",
					"/eltern/ankuendigungen",
					"/sekretariat/ankuendigungen",
					"/sekretariat/alleankuendigungen",
					"/sekretariat/neueankuendigungallgemein",
					"/sekretariat/neueankuendigungrolle",
					"/lehrender/neueankuendigungklasse",
					"/sekretariat/ankuendigungen/edit/{notificationId}",
					"/sekretariat/ankuendigungloeschen/{notificationId}",
					"/sekretariat/krankmeldungen",
					"/sekretariat/krankmeldungen/{illnessNotificationId}",
					"/lehrender/krankmeldungen/neuekrankmeldung",
					"/eltern/krankmeldungen/neuekrankmeldung",
					"/lehrender/pruefungen",
					"/lehrender/neuepruefung",
					"/lehrender/pruefung/{examId}",
					"/lehrender/pruefung/edit/{examId}",
					"/lehrender/pruefung/delete/{examId}",
					"/sekretariat/klassenliste/{classId}",
					"/sekretariat/klassenliste/keineklasse",
					"/sekretariat/klassenliste/keineklasse/klassehinzufuegen",
					"/sekretariat/klassenliste/klasseaendern",
					"/lehrender/klassenliste/{classId}",
					"/lehrender/klassenliste/abwesenheiteintragen",
					"/lehrender/noten/eintragen",
					"/lernender/noten/{studentId}",
					"/eltern/noten/{parentId}",
					"/lehrender/noten/neuesnotenschema",
					"/lernender/noten/notenschema/{studentId}",
					"/eltern/noten/notenschema/{parentId}",
					"/eltern/praesenz/{parentId}"
        		)
                .permitAll().anyRequest().authenticated()
                .and().exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);;
    }
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(ImmutableList.of("*"));
        configuration.setAllowedMethods(ImmutableList.of("HEAD",
                "GET", "POST", "PUT", "DELETE", "PATCH"));
        // setAllowCredentials(true) is important, otherwise:
        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
        //configuration.setAllowCredentials(true);
        // setAllowedHeaders is important! Without it, OPTIONS preflight request
        // will fail with 403 Invalid CORS request
        configuration.setAllowedHeaders(ImmutableList.of("Authorization", "Cache-Control", "Content-Type"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}

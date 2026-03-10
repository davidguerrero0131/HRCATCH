package com.HUSRTbdBiomedica;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import com.HUSRTbdBiomedica.service.CustomAccessDeniedHandler;
import com.HUSRTbdBiomedica.service.CustomUserDetailsService;
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public UserDetailsService userDetailsService(){
		return new CustomUserDetailsService();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		
	
		return new BCryptPasswordEncoder();
	}
	@Bean
	AccessDeniedHandler accessDeniedHandler() {
		return new CustomAccessDeniedHandler();
		
	}
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService());
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		
		return provider;
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.authenticationProvider(authenticationProvider());
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.authorizeRequests().antMatchers("/","/css/**","/js/**","/images/**","/scss/**").permitAll()
			.antMatchers("/eliminarbaja/**","/asignarcodigorand").hasAuthority("ADMIN")
			.antMatchers("/darbaja/**","/programar","/nuevoequipomtto","/editarportipo","/editarequipomtto/**",
					"/crearnuevoequipo/**","/listarhvrepeat/**","/eliminarHV/**","/editreporte/**").hasAnyAuthority("ADMIN","EDITOR")
			.antMatchers("/clasificacionDHServicio", "/nuevollamado/*", "/servicio/*", "/visualizacionreportes/*", "/clasificacion",
					"/clasificacionIntensidadUso", "/clasificacionctEdad", "/clasificacionctNMantenimientos",
					"/clasificacionTFueraServicio", "/clasificacionctSeguridad", "/clasificacionctGarantia",
					"/clasificacionDHTipoEquipos", "/visualpdfguia/**", "/downloadHV/**", "/visualpdfcalibracion/**", "/visualpdfreport/**",
					"/downlandHVSYS/**", "/matrizhv/**", "/hvdocumentosequipo/**", "/visualpdfdocumento/**",
					"/formatohojadevida/**", "/guiaequipo/**", "/hvdocumentosequipo/**", "/exportplan").permitAll()
			.antMatchers("/listarbajas","/formatoreportebaja/**","/reportesbaja/**","/buscarhvbaja/**"
					,"/paradarbaja","/calendario","/semaforizacionportipoequipo","/semaforizacionporservicio"
					,"/equiposparasemaforizarservicio/**","/buscarultimaevaluacion","/equiposparasemaforizar/**"
							,"/resultadosevaluacionporequipos","/ultimosresultadosevtecnica/**","/todoslosequipos"
							,"/semaforizacion","/trimestral","/cuatrimestral","/price","/anual","/buscadoporserie"
							,"/listinactivos","/nuevohvind","/nuevohvind/**","/editarhvind/**","/listarhvind"
							,"/formatohvind/**","/nuevoreporteotro","/visualizacionotrosreportes"
							,"/nuevoequipo/**","/editarequipo/**","/nuevoequipo/**"
							,"/hvdatosequipo/**","/hvdatoscompra/**","/hvdatostecnicos/**"
							,"/hvdatosregulatorios/**","/hvclasificacionequipo/**","/hvmantenimientoequipo/**"
							,"/hvaccesorios/**","/indicadores","/mantenimiento","/rutinamtto/**/**"
							,"/nuevopreventivo/**/**","/rutinaformatomtto/**","/uploadreport/**","/allReportes"
							,"/visualizaciontipoequipo/**","/visualizacionequiposanuales/**"
							,"/visualizacionequipossemestrales/**","/visualizacionequiposcuatrimestrales/**"
							,"/visualizacionequipostrimestrales/**","/formatoreporte/**"
							,"/nuevoreporte/**","/download/**","/usuarios","/reporteselect", "/uploaddocument/**").hasAnyAuthority("ADMIN","EDITOR","USER")
			
			.antMatchers("/syshome","/systipos","/syserie","/sysnuevoequipobytipo","/editarsysequipo/**","/sysnuevoequipo/**"
					,"/syseditarequipo/**","/sysmantenimiento","/sysnuevahv/**","/sysedithv/**","/sysindicadores","/sysnuevaentega/**"
					,"/sysnuevomtto/**","/sysusuarios","/sysexport").hasAnyAuthority("ADMIN","SYSADMIN","SYSUSER")
			.antMatchers("/asignarcodigosys").hasAnyAuthority("ADMIN","SYSADMIN")
			.antMatchers("/sysvisitante").hasAnyAuthority("SYSVISITOR")
			
			.anyRequest()
			.authenticated()
			.and()
			.formLogin()
				.loginPage("/signinHUSRT")
				.permitAll()
				.defaultSuccessUrl("/producto")
				.failureUrl("/signinHUSRT?error=true")
				.usernameParameter("cedula")
				.passwordParameter("password")
				
				.permitAll()
				.and()
				.exceptionHandling().accessDeniedHandler(accessDeniedHandler())
				.and()
			.logout()
				.permitAll()
				.logoutSuccessUrl("/signinHUSRT?logout");
	}
	
	

	
	
	
	
	
	
	

}

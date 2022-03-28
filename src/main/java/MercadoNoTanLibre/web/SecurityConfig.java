package MercadoNoTanLibre.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter { 
    /* Bean :configurerGlobal
    Bean :userDetailsService 
    configurerGlobal necesita el bean userDetailsService
    */
    /*mientras tenga estos dos no puedo usar los admin 123,user 123456*/
    /*@Autowired
    private UserDetailsService userDetailsService;
    
    @Bean/*Para encryptar la contraseña
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }*/
    
    @Override/*aca ecripto la contraseña del usuario*/
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        
        
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("{noop}123")
                .roles("ADMIN", "USER")
                .and()
                .withUser("user")
                .password("{noop}123456")
                .roles("USER")
                ;
    }
    
    
    

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/agregarProductoYCategoria").hasRole("ADMIN")
                .and()
                .formLogin()
                .loginPage("/login").defaultSuccessUrl("/", true)
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/")
                .and()
                .exceptionHandling().accessDeniedPage("/errores/error403");/*Pagina en caso de no tener acceso, no me funcionaba hasta que agrege la direccion en el addViewControllers del webConfig */

    }
    
    
    
   
    
}
/*
Si dejo hasAnyRole("USER","ADMIN")  no me deja entrar en la pagina de inicio porque bueno, no estamos registrados, con el anonymos pasa al revez jajaajja
Cambiel antMatchers, en vez de (/) lo puse en ("/agregarProductoYCategoria") ,Solucionado
*/


/*para que es el anonymous? es para que cualquiera sin ser un usuario 
pueda entrar en esta pagina*//*cuando me registro en la pagina no me deja entrar 
en el inicio y creo que es porque cuando me registro paso a tener un rol, y aca marca que es solo para anonymus*/
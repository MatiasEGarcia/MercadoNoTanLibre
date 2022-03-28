

package MercadoNoTanLibre.web;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

    @Override
    public void addViewControllers(ViewControllerRegistry registro){
       registro.addViewController("/errores/error403").setViewName("/errores/error403");
                
    }

    //para las imagenes
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        WebMvcConfigurer.super.addResourceHandlers(registry);
        
        //primero el recurso, osea las imagenes y despues su localizacion
        registry.addResourceHandler("/imgData/**").addResourceLocations("file:/C:/Users/Matias/Desktop/Proyectos/Proyectos subidos a github/MercadoNoTanLibre3/src/main/resources/static/imgData/");
    }
}

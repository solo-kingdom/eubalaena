package pub.wii.eubalaena.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pub.wii.common.spring.interceptor.AuthInterceptor;

@Configuration
@EnableWebMvc
public class AppConfig implements WebMvcConfigurer {
    AuthInterceptor authInterceptor;

    public AppConfig(AuthInterceptor authInterceptor) {
        this.authInterceptor = authInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}

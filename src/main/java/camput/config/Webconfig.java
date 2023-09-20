package camput.config;

import camput.intercepter.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class Webconfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/camput/home/**"

                        ,"/camput/member/join","/camput/login","/camput/findId","/camput/findId/modal","/camput/findPw/id","/camput/findPw","/camput/logout"
                        ,"/camput/login/api-login","/camput/main","/camput/member/join2","/api"
                        ,"/camput/detail/{name}","/camput/detail/**"
                        ,"/camput/Qna/**"
                        ,"/js/**","/css/**","/*.png","/img/**","/error","/ico/**","/*.jpg","/*.tmp","/favicon.ico","/done/css/**", "/camputFront/**"
                        ,"/fragments/**","/layout/**");
    }
}

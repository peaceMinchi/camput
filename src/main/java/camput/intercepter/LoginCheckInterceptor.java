package camput.intercepter;

import camput.MemberSession;
import camput.Service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();

        log.info("인증체크 인터셉터 실행 {}",requestURI);
        HttpSession session = request.getSession(false);

        if(session==null||session.getAttribute(MemberSession.LOGIN_MEMBER)==null){
            log.info("미인증 사용자 요청");
            response.sendRedirect("/camput/login?redirectURL=" + requestURI);
            return false;
        }else if(session==null||(session.getAttribute(MemberSession.LOGIN_MEMBER)==null)){
            log.info("미인증 사용자 요청");
            response.sendRedirect("/camput/login?redirectURL=" + requestURI);
            return false;
        }
        return true;
    }
}
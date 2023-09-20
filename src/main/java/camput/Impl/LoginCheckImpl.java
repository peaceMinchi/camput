package camput.Impl;

import camput.Dto.loginApiDto.LoginSessionDto;
import camput.MemberSession;
import camput.Service.LoginCheckService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginCheckImpl implements LoginCheckService {
    @Override
    public String checkLogin(HttpServletRequest request) {
        if(request.getSession().getAttribute(MemberSession.LOGIN_MEMBER)!=null){
            HttpSession session = request.getSession();
            LoginSessionDto loginSessionDto =(LoginSessionDto) session.getAttribute("loginMember");
            String loginId = loginSessionDto.getLoginId();
            return loginId;
        }
       return null;
    }
}

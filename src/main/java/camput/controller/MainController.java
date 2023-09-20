package camput.controller;

import camput.Dto.DetailPageDto;
import camput.Service.LoginCheckService;
import camput.Service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/camput")
public class MainController {
    private final MemberService memberService;
    private final LoginCheckService loginCheckService;
    @GetMapping("/main") // 쿠키를 받자
    public String homeLogin(Model model, HttpServletRequest request) {
        String loginId = loginCheckService.checkLogin(request);
        System.out.println(loginId);
        if (loginId == null) { // 로그인 값이 없으면
            return "/main";
        }
        model.addAttribute("member", loginId);
        log.info("loginMember", loginId);
        return "/main";  // 로그인 값이 있으면
    }

}

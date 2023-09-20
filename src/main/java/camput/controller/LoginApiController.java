package camput.controller;

import camput.Service.KakaoLoginService;
import camput.Service.LoginApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/camput")
@RequiredArgsConstructor
@Slf4j
public class LoginApiController {

 private final KakaoLoginService kakaoLoginService;
    @GetMapping("/login/api-login")
    public String doLoginApi(@RequestParam String code, HttpServletRequest request, HttpSession session){
        HttpSession httpSession = kakaoLoginService.doApiLogin(code, session, request);
        session=httpSession;
        return "redirect:/camput/home";
    }
}

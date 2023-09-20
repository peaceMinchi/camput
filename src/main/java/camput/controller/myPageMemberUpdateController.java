package camput.controller;

import camput.Dto.MemberJoinDto;
import camput.Impl.MemberUpdateImpl;
import camput.Service.JoinService;
import camput.Service.LoginCheckService;
import camput.Service.MemberUpdateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Slf4j
@Controller
@RequiredArgsConstructor
public class myPageMemberUpdateController {
    private final MemberUpdateService memberUpdateService;
    private final JoinService joinService;
    private final LoginCheckService loginCheckService;

    @GetMapping("/member/update")
    public String intoPage(Model model, HttpServletRequest request){
        String loginId = loginCheckService.checkLogin(request);
        MemberJoinDto result = memberUpdateService.view(loginId);
        model.addAttribute("loginMember", result);
        model.addAttribute("member",loginId);
        return "myPageMemberUpdate";
    }

    @PostMapping("/member/update")
    @ResponseBody
    public String update(@ModelAttribute MemberJoinDto memberJoinDto){
        System.out.println("memberJoinDto = " + memberJoinDto);
        String MemberUpdate = memberUpdateService.update(memberJoinDto);
        //return "/camput/myPage/information";
        return "/camput/myPage/information";
    }

}

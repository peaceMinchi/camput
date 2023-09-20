package camput.controller;

import camput.Dto.MemberInfoDto;
import camput.Dto.SearchDto;
import camput.Service.LoginCheckService;
import camput.Service.MemberService;
import camput.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@RequestMapping("/camput")
@Slf4j
public class MyPageController {

    private final MemberService memberService;
    private final LoginCheckService loginCheckService;
    @GetMapping("/myPage/information")
    public String myInformationPage(Model model, HttpServletRequest request){
        String loginId = loginCheckService.checkLogin(request);
        MemberInfoDto memberInfo = memberService.findMemberInfo(loginId);
        String nickName=memberInfo.getName();
        model.addAttribute("memberInfo",memberInfo);
        model.addAttribute("nickName",nickName);
        model.addAttribute("member",loginId);
        return "/myPageMemberInfo";
    }

    @GetMapping("/myPage/information/checkMember")
    @ResponseBody
    public String memberCheck(@RequestParam("memberPw") String memberPw){
        String result = memberService.findMemberByPw(memberPw);
        return result;
    }
}

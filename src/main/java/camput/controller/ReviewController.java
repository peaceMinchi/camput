package camput.controller;

import camput.Dto.CampCommentDto;
import camput.Dto.MemberInfoDto;
import camput.Dto.MemberJoinDto;
import camput.Service.CampCommentService;
import camput.Service.LoginCheckService;
import camput.Service.MemberService;
import camput.Service.MyPageBookInfoService;
import camput.domain.Camput;
import camput.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/camput")
public class ReviewController {
    private final CampCommentService campCommentService;
    private final LoginCheckService loginCheckService;
    private final MemberService memberService;

    @PostMapping("/reviews/insert") // 새글
    @ResponseBody
    public Boolean createReview(@ModelAttribute CampCommentDto commentDto, HttpServletRequest request) {
        String loginId = loginCheckService.checkLogin(request);
        MemberInfoDto memberInfoDto = memberService.findMemberInfo(loginId);
        Member member = Member.builder()
                .memberLoginId(memberInfoDto.getMemberLoginId())
                .nickName(memberInfoDto.getNickName())
                .id(memberInfoDto.getId())
                .build();
        commentDto.setMember(member);
        campCommentService.save(commentDto);
        return true;
    }
   @PostMapping("/reviews/update") // 수정 - 아이디로 불러와서 수정
   @ResponseBody
    public Boolean updateReview(@ModelAttribute CampCommentDto commentDto, HttpServletRequest request) {
       String loginId = loginCheckService.checkLogin(request);
       MemberInfoDto memberInfoDto = memberService.findMemberInfo(loginId);
       Member member = Member.builder()
               .memberLoginId(memberInfoDto.getMemberLoginId())
               .nickName(memberInfoDto.getNickName())
               .id(memberInfoDto.getId())
               .build();
       commentDto.setMember(member);
       campCommentService.update(commentDto);
       return true;
   }

    @GetMapping("/reviews/delete") // 삭제        /   @PathVariable    @RequestParam
    @ResponseBody
    public Boolean deleteReview(@RequestParam("id") Long id) {
        campCommentService.deleteById(id);
        return true;
    }
//
//    @GetMapping("/campDetail/{name}") // 리스트 아이디로 불러오기
//    public String getReviews(@PathVariable Long Id, Model model) {
//        model.addAttribute("Commented");
//        return "reviews";
//    }
//    @GetMapping("/reviews")
//    public String getReviews(Model model) {
//        model.addAttribute("reviews", campCommentService.findAllByCamput());
//        model.addAttribute("avgRating", campCommentService.ReviewService(getReviews()));
//        return "reviews";
//    }
}

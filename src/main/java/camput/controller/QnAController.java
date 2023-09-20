package camput.controller;

import camput.Dto.QnADto;
import camput.Dto.QnaAnswerDto;
import camput.Service.LoginCheckService;
import camput.Service.QnAService;
import camput.domain.Qna;
import camput.domain.QnaAnswer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class QnAController {

    private final QnAService qnaService;
    private final LoginCheckService loginCheckService;

    @GetMapping("/QnA/write")
    public String intoBoardWrite(HttpServletRequest request,Model model) {
        String memberId = loginCheckService.checkLogin(request);
        model.addAttribute("member",memberId);
        return "QandAWrite";
    }

    @PostMapping("/QnA/writepro") // 작성글 저장
    public String qnaWritePro(@ModelAttribute QnADto qnaDto, HttpServletRequest request){ // 내용 받아오기
        //String writer = (String) session.getAttribute("loginMember");
        String memberId = loginCheckService.checkLogin(request);
        qnaDto.setMemberLoginId(memberId);
        qnaService.save(qnaDto);
        return "redirect:/QnA/list";
    }

    @GetMapping("/QnA/list") // 게시판 리스트
    public String qnaUpdate(Model model, HttpServletRequest request, Pageable pageable, @ModelAttribute QnADto qnaDto){
        Page<QnADto> result = qnaService.qnaListWithPaging(pageable, qnaDto);
        model.addAttribute("qnalist", result); // list라는 이름으로 viewQnAList()를 html로 보낸다.
        model.addAttribute("totalPage", result.getTotalPages() - 1);
        String memberId = loginCheckService.checkLogin(request);
        model.addAttribute("member",memberId);
        //String loginMember = (String) session.getAttribute("loginMember");
        model.addAttribute("loginMember", memberId);
        return "QandA";
    }

    @GetMapping("/QnA/view") // 게시판 상세페이지
    public String qnaview(Model model, Long id, HttpServletRequest request){
        Qna result = qnaService.qnaView(id);
        model.addAttribute("qna", result);
        //1. 세션 정보 가져오기
        String memberId = loginCheckService.checkLogin(request);
        //String loginMember = (String) session.getAttribute("loginMember");
        //2. 세션 넘겨주기
        model.addAttribute("loginMember", memberId);
        model.addAttribute("member", memberId);
        //3. 조회수 증가
        qnaService.updateViewCount(id);
        //4. 답글 조회
        List<QnaAnswerDto> qnaAnswerList = qnaService.selectQnaAnswer(id);
        model.addAttribute("member",memberId);
        model.addAttribute("qnaAnswerList", qnaAnswerList);
        return "QandAView";
    }

    @GetMapping("/QnA/delete") // 게시판 삭제
    public String qnaDelete(Long id){
        qnaService.qnaDelete(id);
        return "redirect:/QnA/list";
    }

    @GetMapping("/QnA/modify/{id}") // 패스베이러블
    public String qnaModify(@PathVariable("id") Long id, Model model){
        model.addAttribute("qna", qnaService.qnaView(id));
        Qna qna = qnaService.qnaView(id);
        return "QandAUpdate";
    }

    @PostMapping("/QnA/update/{id}")
    public String qnaUpdate(@PathVariable("id") Long id,@ModelAttribute QnADto qnaDto, HttpServletRequest request){
        System.out.println(qnaDto);
        String memberId = loginCheckService.checkLogin(request);
        qnaDto.setMemberLoginId(memberId);
        String result = qnaService.qnaUpdate(qnaDto);

        return "redirect:/QnA/list";
    }

    @PostMapping("/QnA/insert/answer")
    public String insertQnaAnswer(@ModelAttribute QnADto qnaDto, HttpServletRequest request) {
        //String loginMember = (String) session.getAttribute("loginMember");
        String memberId = loginCheckService.checkLogin(request);
        qnaDto.setMemberLoginId(memberId);
        qnaService.insertQnaAnswer(qnaDto);
        return "redirect:/QnA/view?id=" + qnaDto.getId();
    }

    @GetMapping("/QnA/delete/answer/{id}/{qnaId}") // 게시판 댓글 삭제
    public String qnaDeleteAnswer(@PathVariable("id") Long id, @PathVariable("qnaId") Long qnaId){
        qnaService.qnaDeleteAnswer(id);
        return "redirect:/QnA/view?id=" + qnaId;
    }

    @PostMapping("/QnA/update/answer") // 게시판 댓글 수정
    public String qnaUpdateAnswer(@ModelAttribute QnaAnswerDto qnaAnswerDto) {
        qnaService.qnaUpdateAnswer(qnaAnswerDto.getId(), qnaAnswerDto.getQAnswer());
        return "redirect:/QnA/view?id=" + qnaAnswerDto.getQnaId();
    }
}

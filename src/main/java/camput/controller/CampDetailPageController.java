package camput.controller;

import camput.Dto.CampCommentDto;
import camput.Dto.DetailPageDto;
import camput.Dto.LikeDto;
import camput.Service.*;
import camput.domain.Camput;
import camput.domain.Commented;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/camput")
@Slf4j
public class CampDetailPageController {
    private final CamputService camputService;
    private final CampCalenderService campCalenderService;
    private final LikeService likeService;
    private final LoginCheckService loginCheckService;


    @GetMapping("/detail/{name}")

    public String detailPageForm(@PathVariable("name") String name, Model model,HttpServletRequest request){
        log.info("name={}",name);
        String loginId = loginCheckService.checkLogin(request);
        DetailPageDto camp = camputService.show(name,loginId);
        List<LocalDate> localDates = campCalenderService.campBookedCalender(name);
        log.info("like={}",camp.getLike());
        model.addAttribute("localDates",localDates);
        model.addAttribute("camp",camp);
        model.addAttribute("loginId", loginId);
        model.addAttribute("member",loginId);
        return "campDetail";
    }

    @PostMapping("/detail/like")
    @ResponseBody
    public LikeDto like(@RequestBody String campName,HttpServletRequest request) throws ParseException {
        String loginId = loginCheckService.checkLogin(request);
        LikeDto likeDto = likeService.like(loginId, campName);

        return likeDto;
    }
}

package camput.controller;

import camput.Dto.CancelShowInfoDto;
import camput.Service.CancelService;
import camput.Service.LoginCheckService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@RequestMapping("/camput")
@Slf4j
public class ReservationCancelController {
    private final CancelService cancelService;
    private final LoginCheckService loginCheckService;
    @GetMapping("/reservation/cancel")
    public String reservationCancelForm(@RequestParam("campName")String campName,
                                        @RequestParam("bookedDay")String bookedDay,
                                        HttpServletRequest request, Model model){
        String loginId = loginCheckService.checkLogin(request);
        CancelShowInfoDto info = cancelService.showCancelInfo(campName, loginId, bookedDay);
        model.addAttribute("info",info);
        model.addAttribute("member",loginId);
        return "cancel";
    }

    @PostMapping("/rservation/cancel/deleteReaervation")
    @ResponseBody
    public String deleteReservation(@RequestBody String cancelInfo, HttpServletRequest request) throws ParseException {
        String loginId = loginCheckService.checkLogin(request);
        String result = cancelService.cancel(cancelInfo, loginId);
        return result;
    }
}

package camput.controller;

import camput.Dto.CategoryDto;
import camput.Dto.MapCampDto;
import camput.Dto.MemberInfoDto;
import camput.Dto.SearchDto;
import camput.Dto.loginApiDto.LoginApiRequestDto;
import camput.Dto.loginApiDto.LoginSessionDto;
import camput.Service.LoginCheckService;
import camput.Service.MapCampService;
import camput.repository.CamputRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequiredArgsConstructor
@RequestMapping("/camput")
@Slf4j
public class HomeController {
    private final MapCampService mapCampService;
    private final LoginCheckService loginCheckService;
    @GetMapping("/home")
    public String homeForm(Model model, HttpServletRequest request){
        String memberId = loginCheckService.checkLogin(request);
        List<MapCampDto> mapCampDtoList = mapCampService.bestFiveCamp();
        List<MapCampDto> camps = mapCampService.findCamps();
        model.addAttribute("campList",camps);
        model.addAttribute("topFive",mapCampDtoList);
        model.addAttribute("member",memberId);
        return "home";
    }

    private static void checkLogin(Model model, HttpServletRequest request) {

    }

    @GetMapping("/home/category")
    @ResponseBody
    public List<MapCampDto> categoryCamp(@ModelAttribute CategoryDto categoryDto){
        log.info(categoryDto.getAnimalCheck());
        List<MapCampDto> mapCampDtoList = mapCampService.categoryCamps(categoryDto);

        return mapCampDtoList;
    }

    @GetMapping("/home/search")
    @ResponseBody
    public List<MapCampDto> searchCamps(@RequestParam String searchInputText){
        log.info("실행");
        log.info("searchInputText={}",searchInputText);
      List<MapCampDto> mapCampDtoList = mapCampService.searchCamp(searchInputText);
        if(mapCampDtoList==null){
         return null;
        }else {
            return mapCampDtoList;
        }
    }

    @GetMapping("/home/reset")
    @ResponseBody
    public List<MapCampDto> reset(){
        List<MapCampDto> all = mapCampService.findAll();
        return all;
    }
}


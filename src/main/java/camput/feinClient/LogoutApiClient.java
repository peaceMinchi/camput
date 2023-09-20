package camput.feinClient;

import camput.config.LoginApiConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(value = "kakaologout", url="https://kapi.kakao.com", configuration = {LoginApiConfig.class})
public interface LogoutApiClient {
    @GetMapping("/v1/user/logout")
    ResponseEntity<String> doLogOut(@RequestHeader Map<String, String> header);
}

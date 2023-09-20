package camput.feinClient;

import camput.config.LoginApiConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient( value="kakoMemberInfo", url="https://kapi.kakao.com", configuration = {LoginApiConfig.class})
public interface LoginApiMemberInfoClient {
    @GetMapping("/v2/user/me")
    ResponseEntity<String> getUserInfo(@RequestHeader Map<String, String> header);
}

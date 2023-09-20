package camput.Impl;

import camput.Dto.loginApiDto.KakaoLoginResponse;
import camput.Dto.loginApiDto.KakaoLoginResponse.KakaoLoginData;
import camput.Dto.loginApiDto.MemberResponseDto;
import camput.Dto.loginApiDto.SocialAuthResponse;
import camput.Service.LoginApiService;
import camput.feinClient.LoginApiClient;
import camput.feinClient.LoginApiMemberInfoClient;
import camput.feinClient.LogoutApiClient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginApiImpl implements LoginApiService {

    private final LoginApiClient loginApiClient;
    private final LoginApiMemberInfoClient loginApiMemberInfoClient;
    private final LogoutApiClient logoutApiClient;
    @Value("${social.client.kakao.rest-api-key}")
    private String clientId;
    @Value("${social.client.kakao.secret-key}")
    private String clientSecret;
    @Value("${social.client.kakao.grant_type}")
    private String grantType;
    @Value("${social.client.kakao.redirect-uri}")
    private String redirectUri;

    @Override
    public SocialAuthResponse getAccessToken(String code) {
        ResponseEntity<?> accessToken = loginApiClient.getAccessToken(grantType,clientId,clientSecret,redirectUri,code);
        log.info("accessToken={}",accessToken.toString());

        return new Gson().fromJson(String.valueOf(accessToken.getBody()), SocialAuthResponse.class);
    }
    @Override
    public MemberResponseDto getUserInfo(String token) {

        Map<String, String> header = new HashMap<>();
        header.put("authorization","Bearer " + token);
        
        ResponseEntity<?> response = loginApiMemberInfoClient.getUserInfo(header);
        
        log.info("response={}",response);
        String jsonString = response.getBody().toString();
       
        Gson gson = new Gson();

        KakaoLoginResponse KakaoLoginResponse = gson.fromJson(jsonString, KakaoLoginResponse.class);
        KakaoLoginData kakaoLoginData = Optional.ofNullable(KakaoLoginResponse.getKakao_account()).orElse(KakaoLoginData.builder().build());
        String nickname = Optional.ofNullable(kakaoLoginData.getProfile()).orElse(KakaoLoginData.KakaoProfile.builder().build()).getNickname();

        return  MemberResponseDto.builder()
                .loginId(kakaoLoginData.getEmail())
                .nickname(nickname)
                .birthday(kakaoLoginData.getBirthday())
                .gender(kakaoLoginData.getGender())
                .build();

    }
    @Override
    public void logOut(String code){
        Map<String, String> header = new HashMap<>();
        log.info("code={}",code);
        header.put("authorization", "Bearer "+code);

        ResponseEntity<?> response = logoutApiClient.doLogOut(header);
        log.info("response={}",response.toString());
    }
}

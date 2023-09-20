package camput.Service;

import camput.Dto.loginApiDto.MemberResponseDto;
import camput.Dto.loginApiDto.SocialAuthResponse;
import camput.feinClient.LoginApiClient;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


public interface LoginApiService {
    SocialAuthResponse getAccessToken(String code);
    MemberResponseDto getUserInfo(String token);
    void logOut(String code);

}




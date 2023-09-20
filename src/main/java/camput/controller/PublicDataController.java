package camput.controller;

import camput.Service.PublicDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequiredArgsConstructor
public class PublicDataController {

    private final PublicDataService publicDataService;
    @GetMapping("/api")
    public String callApi() throws IOException {
        StringBuilder result = new StringBuilder();

        String urlStr="http://apis.data.go.kr/B551011/GoCamping/basedList?"+
                "serviceKey=fJhT4s5Dt%2BNKOVa4IM2C7KnCHsFyoGRPfSfUxPiJEiX6JVhj4hyLKSBBfbh23ysFWAcYT3Hq%2F7%2FkNCO14gfWqw%3D%3D" +
                "&numOfRows=3310"+
                "&pageNo=1"+
                "&MobileOS=ETC"+
                "&MobileApp=AppTest"+
                "&_type=json";
        URL url= new URL(urlStr);

        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream(),"UTF-8"));
        result.append(br.readLine());

        httpURLConnection.disconnect();
        publicDataService.init(result.toString());
        return result.toString();
}
}

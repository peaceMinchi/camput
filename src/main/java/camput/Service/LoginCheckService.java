package camput.Service;

import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

public interface LoginCheckService {
    String checkLogin( HttpServletRequest request);
}

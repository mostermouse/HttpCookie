package com.cookie.httpcookie.service;

import com.cookie.httpcookie.db.UserRepository;
import com.cookie.httpcookie.model.LoginRequest;
import com.cookie.httpcookie.model.UserDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    public void login(
            LoginRequest loginRequest,
            HttpServletResponse httpServletResponse
    ){
        var id = loginRequest.getId();
        var pw = loginRequest.getPassword();

        var optionalUser = userRepository.findByName(id);

        if(optionalUser.isPresent()){
            var userDto = optionalUser.get();
            if(userDto.getPassword().equals(pw)){
                //cookie 해당 정보 저장
                var cookie = new Cookie("atuorizetion-cookie", userDto.getId());
                cookie.setDomain("localhost");
                cookie.setPath("/");
                //못 읽게 보안 처리 해줌
                cookie.setHttpOnly(true);
                cookie.setMaxAge(-1); //session
                httpServletResponse.addCookie(cookie);
            }


        }else{
            throw new RuntimeException("User Not Found");
        }
    }
}

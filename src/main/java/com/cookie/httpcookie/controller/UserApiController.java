package com.cookie.httpcookie.controller;

import com.cookie.httpcookie.db.UserRepository;
import com.cookie.httpcookie.model.UserDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserApiController {
    private final UserRepository userRepository;

    @GetMapping("/me")
    public UserDto me(
            HttpServletRequest httpServletRequest,
            @CookieValue(name = "atuorizetion-cookie", required = false)
            String authorizationCooke
    ) {
        log.info("authorizationCookie : {}", authorizationCooke);
        var optionalUserDto = userRepository.findById(authorizationCooke);
        return optionalUserDto.get();


       /* var cookies = httpServletRequest.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                log.info("key : {} , value : {}",cookie.getName(), cookie.getValue());
            }
        }*/
    }

    @GetMapping("/me2")
    public UserDto me2(
            @RequestHeader(name = "authorization" , required = false)
            String authorizationHeard
    ) {
        log.info("authorizationHeard : {}", authorizationHeard);
        var optionalUserDto = userRepository.findById(authorizationHeard);
        return optionalUserDto.get();


    }
}

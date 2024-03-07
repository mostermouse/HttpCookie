package com.cookie.httpcookie.db;

import com.cookie.httpcookie.model.UserDto;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserRepository {
    private final List<UserDto> uerList = new ArrayList<>();
    public Optional<UserDto> findById(String id){
        return uerList.stream()
                .filter(it -> {
                    return it.getId().equals(id);
                })
                .findFirst();
    }
    public Optional<UserDto> findByName(String name){
        return uerList.stream()
                .filter(it -> {
                    return it.getName().equals(name);
                })
                .findFirst();
    }

    @PostConstruct
    public void start() {
        uerList.add(
                new UserDto(
                        //랜덤 생성
                        UUID.randomUUID().toString(),
                        "홍길동",
                        "1234"
                )
        );
        uerList.add(
                new UserDto(
                        //랜덤 생성
                        UUID.randomUUID().toString(),
                        "유관순",
                        "1234"
                )
        );
        uerList.add(
                new UserDto(
                        //랜덤 생성
                        UUID.randomUUID().toString(),
                        "조정인",
                        "1234"
                )
        );
    }
}

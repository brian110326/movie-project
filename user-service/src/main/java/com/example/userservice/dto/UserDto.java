package com.example.userservice.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserDto {

    private String email;
    private String username;
    private String password;
    private String userId;
    private Date createdAt;

    // 예매한 영화 List 추가

}

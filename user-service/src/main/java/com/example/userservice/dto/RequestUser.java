package com.example.userservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Requested User Object for User Add")
public class RequestUser {

    @NotNull(message = "Email cannot be null")
    @Size(min = 2, message = "Email not be less than two characters")
    @Email
    @Schema(title = "사용자 Email", description = "사용자 ID로 사용되는 Email 정보로 로그인 시 사용")
    private String email;

    @NotNull(message = "Password cannot be null")
    @Size(min = 8, message = "Password must be equal or grater than 8 characters")
    @Schema(title = "사용자 암호", description = "로그인시 사용되는 사용자 암호")
    private String password;

    @NotNull(message = "Name cannot be null")
    @Size(min = 2, message = "Name not be less than two characters")
    @Schema(title = "사용자 이름", description = "사용자 이름")
    private String username;

}

package com.housing.dto.response.auth;

import com.housing.common.ResponseCode;
import com.housing.common.ResponseMessage;
import com.housing.dto.response.ResponseDTO;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class SignInResponseDto extends ResponseDTO {

    private String token;

    private int expirationTime;

    private SignInResponseDto(String token) {
        super();
        this.token = token;
        this.expirationTime = 3600;
    }

    public static ResponseEntity<SignInResponseDto> success (String token) {

        SignInResponseDto responseBody = new SignInResponseDto(token);

        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDTO> signInFail() {

        ResponseDTO responseBody = new ResponseDTO(ResponseCode.SIGN_IN_FAIL, ResponseMessage.SIGN_IN_FAIL);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }

}

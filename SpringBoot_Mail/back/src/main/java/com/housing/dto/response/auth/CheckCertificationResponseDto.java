package com.housing.dto.response.auth;

import com.housing.common.ResponseCode;
import com.housing.common.ResponseMessage;
import com.housing.dto.response.ResponseDTO;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;

@Getter
public class CheckCertificationResponseDto extends ResponseDTO {

    private CheckCertificationResponseDto() {
        super();
    }

    public static ResponseEntity<CheckCertificationResponseDto> success() {
        CheckCertificationResponseDto responseBody = new CheckCertificationResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDTO> certificationFail() {

        ResponseDTO responseBody = new ResponseDTO(ResponseCode.CERTIFICATION_FAIL, ResponseMessage.CERTIFICATION_FAIL);

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }
}

package com.app.global.error;

import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

@Getter
@Builder
public class ErrorResponse {
    private String errorCode;
    private String errorMessage;

    public static ErrorResponse of(String errorCode, String errorMessage) {
        return ErrorResponse.builder()
                .errorCode(errorCode)
                .errorMessage(errorMessage)
                .build();
    }

    public static ErrorResponse of(String errorCode, BindingResult bindingResult) { //BindingResult에 에러 정보가 들어감
        return ErrorResponse.builder()
                .errorCode(errorCode)
                .errorMessage(createErrorMessage(bindingResult))
                .build();
    }

    private static String createErrorMessage(BindingResult bindingResult) {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            if (!isFirst) { //첫번째 에러메시지가 아니라면 콤마로 이어줌
                sb.append(", ");
            } else { //첫번째 에러메시지라면 다음 반복부터 콤마가 이어지게 false로 값 변경
                isFirst = false;
            }
            sb.append("[");
            sb.append(fieldError.getField()); //필드명
            sb.append("]");
            sb.append(fieldError.getDefaultMessage()); //에러메시지
        }
        return sb.toString();
    }
}

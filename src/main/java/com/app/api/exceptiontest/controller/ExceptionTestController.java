package com.app.api.exceptiontest.controller;

import com.app.api.exceptiontest.dto.BindExceptionTestDto;
import com.app.api.exceptiontest.dto.TestEnum;
import com.app.global.error.ErrorCode;
import com.app.global.error.exception.BusinessException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/exception")
public class ExceptionTestController {

    @GetMapping("/bind-exception-test")
    public String bindExceptionTest(@Valid BindExceptionTestDto bindExceptionTestDto) {
        //dto의 validation을 검사하여 문제가 없으면 ok 리턴
        return"ok";
    }

    @GetMapping("/type-exception-test")
    public String typeMismatchException(TestEnum testEnum) {
        //testEnum 값이 enum 클래스에 있는 값인지 검사하여 문제가 없으면 ok 리턴
        return "ok";
    }

    @GetMapping("/business-exception-test")
    public String businessExceptionTest(String isError) {
        if ("true".equals(isError))
            throw new BusinessException(ErrorCode.TEST);
        return "ok";
    }

    @GetMapping("/exception-test")
    public String exceptionTest(String isError) {
        if ("true".equals(isError))
            throw new IllegalArgumentException("exception test");
        return "ok";
    }
}

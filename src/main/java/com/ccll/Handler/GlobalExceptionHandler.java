package com.ccll.Handler;


import com.ccll.common.Exception.BaseException;
import com.ccll.common.Exception.NotLoginException;
import com.ccll.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex){
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    /**
     * 捕获未登录异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result notLoginHandler(NotLoginException ex){
        log.error("异常信息：{}", ex.getMessage());
        return Result.notLoginError(ex.getMessage());
    }

}

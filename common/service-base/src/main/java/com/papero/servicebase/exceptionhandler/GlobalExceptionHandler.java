package com.papero.servicebase.exceptionhandler;





import com.papero.commonutils.R;
import com.papero.commonutils.util.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e){
        e.printStackTrace();
        log.error(ExceptionUtil.getMessage(e));
        return R.error();
    }

    @ExceptionHandler(CustomException.class)
    @ResponseBody
    public R error(CustomException e){
        e.printStackTrace();
        log.error(ExceptionUtil.getMessage(e));
        return R.error().message(e.getMsg()).code(e.getCode());
    }
}

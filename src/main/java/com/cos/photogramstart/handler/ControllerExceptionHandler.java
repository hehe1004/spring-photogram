package com.cos.photogramstart.handler;


import com.cos.photogramstart.handler.ex.CustomValidationException;
import com.cos.photogramstart.util.Script;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {

//    @ExceptionHandler(CustomValidationException.class)
//    public Map<String, String> validationException(CustomValidationException e) {
////        return e.getMessage();
//        return e.getErrorMap();
//
//    }

//    @ExceptionHandler(CustomValidationException.class)
//    public CMRespDto<?> validationException (CustomValidationException e) {
////        return e.getMessage();
////        return e.getErrorMap();
//        return new CMRespDto<Map<String, String>>(-1, e.getMessage(), e.getErrorMap());
//    }
//}


    @ExceptionHandler(CustomValidationException.class)
    public String validationException (CustomValidationException e) {
//        return e.getMessage();
//        return e.getErrorMap();
        //스크립트 사용 팝업
        //CMRespoDto, 스크립트 비교
        //1. 클라이언트에게 응답할때는 스크립트가 좋음
        //2. Ajax 통신 - CMRespDto
        //3. 안드로이드 통신 - CMRespDto
        return Script.back(e.getMessage().toString());
    }
}



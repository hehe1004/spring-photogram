package web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController //파일 응답하는 컨트롤러 (클라이언트가 브라우저면 html)
@Controller //데이터를 응답하는 컨트롤러(클라이언트가 핸드폰이면 데이터)
public class HttpController {


    @GetMapping("/get2")
    public String get() {

        return "get리퀘스트 요청";
    }
    public String post() {

        return "";
    }
    public String put() {

        return "";
    }
    public String delete() {

        return "";
    }
}

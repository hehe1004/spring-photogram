package web;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/test")
public class HttpBodyController {

    @PostMapping("/body1")
    public String xwwwformulencoded(){
        return "key=value 전송옴";



    }

    @PostMapping("/body2")
    public String plaintext(){
        return "plain/text 전송옴";


    }

    @PostMapping("/body3")
    public String applicationjson(){
        return "json 전송옴";


    }


}

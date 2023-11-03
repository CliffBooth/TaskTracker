package bestsoftware.tasktracker;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/home")
    public String getHome(){
        return "index";
    }

    @GetMapping("/test")
    @ResponseBody
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("hello from test");
    }
}

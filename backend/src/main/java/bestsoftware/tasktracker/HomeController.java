package bestsoftware.tasktracker;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("/home")
    @ResponseBody
    public ModelAndView getHome(){
        return new ModelAndView("index.html");
    }

    @GetMapping("/test")
    @ResponseBody
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("hello from test");
    }
}

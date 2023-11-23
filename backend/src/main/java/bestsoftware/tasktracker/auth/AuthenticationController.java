package bestsoftware.tasktracker.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping("/sign-up")
    public ResponseEntity<AuthenticationResponse> signup(
            @RequestBody SignupRequest request
    ) {
        return ResponseEntity.ok(service.signup(request));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<AuthenticationResponse> signin(
            @RequestBody  SigninRequest request
    ) {
        return ResponseEntity.ok(service.signin(request));
    }
}

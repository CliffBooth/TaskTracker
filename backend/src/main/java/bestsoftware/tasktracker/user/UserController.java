package bestsoftware.tasktracker.user;

import bestsoftware.tasktracker.global.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @GetMapping("/{id}")
    ResponseEntity<?> getUserById(@PathVariable Long id) {
        Optional<User> o = userRepository.findById(id);
        User u = o.orElseThrow(() -> new DataNotFoundException("User not found " + id));
        return ResponseEntity.ok(new UserJSON(u));
    }
}

package bestsoftware.tasktracker;

import bestsoftware.tasktracker.user.User;
import bestsoftware.tasktracker.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class TaskTrackerApplication implements CommandLineRunner {
    private final UserRepository userRepository;


    public static void main(String[] args) {
        SpringApplication.run(TaskTrackerApplication.class, args);
    }

    // for debugging
    @Override
    public void run(String... args) throws Exception {
    }
}
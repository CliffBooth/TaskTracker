package bestsoftware.tasktracker.project;


import bestsoftware.tasktracker.global.DataNotFoundException;
import bestsoftware.tasktracker.user.User;
import bestsoftware.tasktracker.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    private User getCurentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByName(authentication.getName()).orElseThrow();
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getProject(@PathVariable Long id) {
        Optional<Project> o = projectRepository.findById(id);
        Project p =  o.orElseThrow(() -> new DataNotFoundException("Project not found " + id));
        return ResponseEntity.ok(p);
    }

    /**
     * get projects, specific to the current user.
     */
    @GetMapping("/my")
    ResponseEntity<?> getUserProjects() {
        User currentUser = getCurentUser();
        return ResponseEntity.ok(projectRepository.findAllByUsers_Id(currentUser.getId()));
    }

    //todo: validate request data
    @PostMapping("/")
    ResponseEntity<?> createProject(@RequestBody CreateProject request) {
        User currentUser = getCurentUser();
        String name = request.getName();
        List<User> owners = new ArrayList<>();
        owners.add(currentUser);
        Project project = Project.builder()
                .owner(owners)
                .users(owners)
                .name(name)
                .build();
        projectRepository.save(project);
        return ResponseEntity.ok(project);
    }

    //TODO
//    @PutMapping("/{id}")
//    ResponseEntity<?> updateProject(@PathVariable Long id, @RequestBody UpdateProject request) {
//
//    }
//
//    @DeleteMapping("/{id}")
//    ResponseEntity<?> deleteProject(@PathVariable Long id) {
//
//    }

}

package bestsoftware.tasktracker.project;


import bestsoftware.tasktracker.board.Board;
import bestsoftware.tasktracker.board.BoardRepository;
import bestsoftware.tasktracker.global.DataNotFoundException;
import bestsoftware.tasktracker.global.IllegalActionException;
import bestsoftware.tasktracker.user.User;
import bestsoftware.tasktracker.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    private User getCurentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByName(authentication.getName()).orElseThrow();
    }

    private Project getOrThrow(Long id) {
        Optional<Project> o = projectRepository.findById(id);
        return o.orElseThrow(() -> new DataNotFoundException("Project not found " + id));
    }

    @GetMapping("/{id}")
    ResponseEntity<?> getProject(@PathVariable Long id) {
        Project p = getOrThrow(id);
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

    @PostMapping("/")
    ResponseEntity<?> createProject(@Validated @RequestBody CreateProject request) {
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

    // any user in the project can update project
    // member of the project can add a board
    // only owner of the project can change members, owners, name.
    @PutMapping("/{id}")
    ResponseEntity<?> updateProject(@PathVariable Long id, @Validated @RequestBody UpdateProject request) {
        User currentUser = getCurentUser();
        Project p = getOrThrow(id);
        if (!p.getOwner().contains(currentUser) && !p.getUsers().contains(currentUser)) {
            throw new IllegalActionException("You are not a member of project " + id);
        }

        List<User> newOwners = userRepository.findAllByNameIn(request.getOwnerNames());
        List<User> newUsers = userRepository.findAllByNameIn(request.getMemberNames());
        //check that all user names are actually correct
        if (newOwners.size() != request.getOwnerNames().size() || newUsers.size() != request.getMemberNames().size()) {
            Set<String> foundNames = newOwners.stream().map(User::getName).collect(Collectors.toSet());
            foundNames.addAll(newUsers.stream().map(User::getName).toList());

            Set<String> notFoundNames = request.getOwnerNames().stream()
                    .filter(el -> !foundNames.contains(el))
                    .collect(Collectors.toSet());
            notFoundNames.addAll(request.getMemberNames().stream().filter(el -> !foundNames.contains(el)).toList());
            throw new DataNotFoundException("Users not found " + notFoundNames);
        }

        List<Board> newBoards = boardRepository.findAllById(request.getBoardIds());
        if (newBoards.size() != request.getBoardIds().size()) {
            Set<Long> foundIds = newBoards.stream().mapToLong(Board::getId).boxed().collect(Collectors.toSet());
            List<Long> notFoundIds = new ArrayList<>(request.getBoardIds());
            notFoundIds.removeAll(foundIds);
            throw new DataNotFoundException("Boards not found " + notFoundIds);
        }

        p.setBoards(newBoards);
        if (p.getOwner().contains(currentUser)) {
            p.setName(request.getName());
            p.setUsers(newUsers);
            p.setOwner(newOwners);
        }

        projectRepository.save(p);

        return ResponseEntity.ok(p);
    }

    // only owner can delete project
    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteProject(@PathVariable Long id) {
        User currentUser = getCurentUser();
        Project p = getOrThrow(id);
        if (!p.getOwner().contains(currentUser)) {
            throw new IllegalActionException("You are not the owner of project " + id);
        }
        projectRepository.delete(p);
        return ResponseEntity.ok(id);
    }

}

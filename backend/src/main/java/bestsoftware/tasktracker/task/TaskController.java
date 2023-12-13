package bestsoftware.tasktracker.task;

import bestsoftware.tasktracker.board.Board;
import bestsoftware.tasktracker.board.BoardRepository;
import bestsoftware.tasktracker.global.DataNotFoundException;
import bestsoftware.tasktracker.global.IllegalActionException;
import bestsoftware.tasktracker.project.Project;
import bestsoftware.tasktracker.user.User;
import bestsoftware.tasktracker.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class TaskController {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByName(authentication.getName()).orElseThrow();
    }

    private Task getOrThrow(Long id) {
        Optional<Task> o = taskRepository.findById(id);
        return o.orElseThrow(() -> new DataNotFoundException("Task not found " + id));
    }

    @PostMapping("")
    ResponseEntity<?> addNewTask(@Validated @RequestBody AddNewTask request) {
        User currentUser = getCurrentUser();
        Board board = boardRepository.findById(request.getBoardId()).orElseThrow(() ->
                new DataNotFoundException("no board with id " + request.getBoardId())
        );
        Project project = board.getProject();

        if(!project.getUsers().contains(currentUser)) {
            throw new IllegalActionException("You are not a user of project " + project.getId());
        }

        Task task = Task.builder()
                .text(request.getText())
                .board(board)
                .users(new ArrayList<>())
                .build();

        taskRepository.save(task);
        TaskJSON response = new TaskJSON(task);
        return ResponseEntity.ok(response);

    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteTask(@PathVariable Long id) {
        User currentUser = getCurrentUser();
        Task task = getOrThrow(id);
        Project project = task.getBoard().getProject();
        if (!project.getUsers().contains(currentUser)) {
            throw new IllegalActionException("You are not a member of the project " + project.getId());
        }
        taskRepository.delete(task);
        return ResponseEntity.ok(task.id);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateTask(@PathVariable Long id, @Validated @RequestBody UpdateTask request) {
        User currentUser = getCurrentUser();
        Task t = getOrThrow(id);
        Project p = t.getBoard().getProject();
        if (!p.getUsers().contains(currentUser)) {
            throw new IllegalActionException("You are not a member of the project " + p.getId());
        }

        Board b = boardRepository.findById(request.getBoardId()).orElseThrow(() ->
                new DataNotFoundException("no board with id " + request.getBoardId())
        );

        if (!p.getId().equals(b.getProject().getId())) {
            throw new IllegalActionException("invalid boardId");
        }

        List<Long> projectUserIds = p.getUsers().stream().map(User::getId).toList();

        if (!new HashSet<>(projectUserIds).containsAll(request.getUserIds())) {
            throw new IllegalActionException("invalid userIds");
        }

        List<User> updatedUsers = userRepository.findAllById(request.getUserIds());

        t.setText(request.getText());
        t.setBoard(b);
        t.setUsers(updatedUsers);

        taskRepository.save(t);
        TaskJSON response = new TaskJSON(t);
        return ResponseEntity.ok(response);
    }
}

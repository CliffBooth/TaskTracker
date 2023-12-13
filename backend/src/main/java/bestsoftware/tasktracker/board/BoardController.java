package bestsoftware.tasktracker.board;

import bestsoftware.tasktracker.global.DataNotFoundException;
import bestsoftware.tasktracker.global.IllegalActionException;
import bestsoftware.tasktracker.project.Project;
import bestsoftware.tasktracker.project.ProjectRepository;
import bestsoftware.tasktracker.task.AddNewTask;
import bestsoftware.tasktracker.task.Task;
import bestsoftware.tasktracker.task.TaskJSON;
import bestsoftware.tasktracker.task.TaskRepository;
import bestsoftware.tasktracker.user.User;
import bestsoftware.tasktracker.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {
    private final ProjectRepository projectRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByName(authentication.getName()).orElseThrow();
    }

    private Board getOrThrow(Long id) {
        Optional<Board> o = boardRepository.findById(id);
        return o.orElseThrow(() -> new DataNotFoundException("Board not found " + id));
    }
    @GetMapping("/{id}")
    ResponseEntity<?> getBoard(@PathVariable Long id) {
        Board b = getOrThrow(id);
        BoardJSON result = new BoardJSON(b);
        return ResponseEntity.ok(result);
    }

    @PostMapping("")
    ResponseEntity<?> createBoard(@Validated @RequestBody CreateBoard request) {
        String title = request.getTitle();
        Project project = projectRepository.findById(request.getProjectId()).orElseThrow(() -> new DataNotFoundException("Project not found" + request.getProjectId()));
//        System.out.println("BEFORE BUILDING BOARD");
        Board board = Board.builder()
                .title(title)
                .project(project)
                .tasks(new ArrayList<>())
                .build();

        boardRepository.save(board);
        BoardJSON response = new BoardJSON(board);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/addNewTask")
    ResponseEntity<?> addTask(@PathVariable Long id,@Validated @RequestBody AddNewTask request) {
        Board board = boardRepository.findById(id).orElseThrow();
        User currentUser = getCurrentUser();
        Project project = board.getProject();

        if(!project.getUsers().contains(currentUser))
        {
            throw new IllegalActionException("You are not a user of project " + project.getId());
        }

//        System.out.println(request.text);
        System.out.println(id);

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
    ResponseEntity<?> deleteBoard(@PathVariable Long id) {
        Board board = boardRepository.findById(id).orElseThrow();
        User currentUser = getCurrentUser();
        Project project = board.getProject();
        if(!project.getOwner().contains(currentUser)) {
            throw new IllegalActionException("You are not the owner of project " + project.getId());
        }
        boardRepository.delete(board);
        return ResponseEntity.ok(id);
    }


}

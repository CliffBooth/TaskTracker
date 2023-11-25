package bestsoftware.tasktracker.board;

import bestsoftware.tasktracker.global.DataNotFoundException;
import bestsoftware.tasktracker.project.Project;
import bestsoftware.tasktracker.project.ProjectJSON;
import bestsoftware.tasktracker.project.ProjectRepository;
import bestsoftware.tasktracker.user.User;
import bestsoftware.tasktracker.user.UserController;
import bestsoftware.tasktracker.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/")
    ResponseEntity<?> createBoard(@Validated @RequestBody CreateBoard request) {
        String title = request.getTitle();
        Optional<Project> project = projectRepository.findById(request.getProjectId());
        System.out.println("BEFORE BUILDING BOARD");
        Board board = Board.builder()
                .title(title)
                .project(project.orElseThrow(() -> new DataNotFoundException("Project not found" + request.getProjectId())))
                .tasks(new ArrayList<>())
                .build();

        boardRepository.save(board);
        BoardJSON response = new BoardJSON(board);
        return ResponseEntity.ok(response);
    }


}

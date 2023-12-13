package bestsoftware.tasktracker.board;

import bestsoftware.tasktracker.project.ProjectJSON;
import bestsoftware.tasktracker.task.TaskJSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BoardJSON {
    private Long id;
    private String title;
    private Long project;
    private List<TaskJSON> tasks;

    public BoardJSON(Board b) {
//        System.out.println(b);
        this.id = b.getId();
        this.title = b.getTitle();
        this.project = b.getProject().getId();
        this.tasks = b.getTasks().stream().map(TaskJSON::new).toList();
    }
}

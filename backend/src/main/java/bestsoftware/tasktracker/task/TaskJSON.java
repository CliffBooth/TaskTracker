package bestsoftware.tasktracker.task;

import bestsoftware.tasktracker.user.UserJSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskJSON {
    private Long id;
    private String text;
    private Long board;
    private List<UserJSON> users;

    public TaskJSON(Task t) {
        System.out.println(t);
        this.id = t.getId();
        this.text = t.getText();
        this.board = t.getBoard().getId();
        this.users = t.getUsers().stream().map(UserJSON::new).toList();
    }
}

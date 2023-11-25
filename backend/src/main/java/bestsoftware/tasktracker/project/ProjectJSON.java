package bestsoftware.tasktracker.project;

import bestsoftware.tasktracker.board.Board;
import bestsoftware.tasktracker.user.User;
import bestsoftware.tasktracker.user.UserJSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectJSON {
    private Long id;
    private String name;
    private List<UserJSON> users;
    private List<UserJSON> owners;
    private List<Long> boards;

    public ProjectJSON(Project p) {
        System.out.println(p);
        this.id = p.getId();
        this.name = p.getName();
        this.users = p.getUsers().stream().map(UserJSON::new).toList();
        this.owners = p.getOwner().stream().map(UserJSON::new).toList();
        this.boards = p.getBoards().stream().map(Board::getId).toList();
    }
}

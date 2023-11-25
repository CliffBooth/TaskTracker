package bestsoftware.tasktracker.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserJSON {
    private Long id;
    private String name;

    public UserJSON(User u) {
        this.id = u.getId();
        this.name = u.getName();
    }
}

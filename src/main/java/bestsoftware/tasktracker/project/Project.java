package bestsoftware.tasktracker.project;


import bestsoftware.tasktracker.board.Board;
import bestsoftware.tasktracker.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Builder
public class Project {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    Long id;

    String name;

    @ManyToMany
    @JoinColumn(name="user_id", referencedColumnName = "id")
    List<User> users;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="owner_id", referencedColumnName = "id")
    List<User> owner;

    @OneToMany
    List<Board> boards;
}

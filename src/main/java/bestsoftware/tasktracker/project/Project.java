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
    @JoinTable(
        name="user_project",
        joinColumns = { @JoinColumn(name="user_id") },
        inverseJoinColumns = { @JoinColumn(name="project_id") }
    )
    List<User> users;

    @ManyToMany
    @JoinTable(
        name="owner_project",
        joinColumns = { @JoinColumn(name="owner_id") },
        inverseJoinColumns = { @JoinColumn(name="project_id") }
    )
    List<User> owner;

    @OneToMany
    List<Board> boards;
}

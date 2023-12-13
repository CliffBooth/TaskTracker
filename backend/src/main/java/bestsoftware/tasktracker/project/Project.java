package bestsoftware.tasktracker.project;


import bestsoftware.tasktracker.board.Board;
import bestsoftware.tasktracker.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

//can't use data, because it can't construct toString() because of the circular reference.
@Setter
@Getter
@EqualsAndHashCode
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
        joinColumns = { @JoinColumn(name="project_id") },
        inverseJoinColumns = { @JoinColumn(name="user_id") }
    )
    List<User> users = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name="owner_project",
        joinColumns = { @JoinColumn(name="project_id") },
        inverseJoinColumns = { @JoinColumn(name="owner_id") }
    )
    List<User> owner = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Board> boards = new ArrayList<>();
}

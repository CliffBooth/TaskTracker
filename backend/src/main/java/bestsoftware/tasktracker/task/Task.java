package bestsoftware.tasktracker.task;

import bestsoftware.tasktracker.board.Board;
import bestsoftware.tasktracker.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@EqualsAndHashCode
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    Long id;

    String text;


    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    Board board;

    @ManyToMany
    @JoinTable(
            name = "user_task",
            joinColumns = { @JoinColumn(name = "task_id")},
            inverseJoinColumns = { @JoinColumn(name = "user_id")}
    )
    List<User> users = new ArrayList<>();

}

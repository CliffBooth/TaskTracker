package bestsoftware.tasktracker.board;

import bestsoftware.tasktracker.task.Task;
import bestsoftware.tasktracker.project.Project;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor
@Builder
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    String title;

    @ManyToOne
    @JoinColumn(name="project_id", nullable = false)
    Project project;

    @OneToMany(mappedBy = "task")
    List<Task> tasks = new ArrayList<>();


}

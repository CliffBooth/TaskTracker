package bestsoftware.tasktracker.board;

import bestsoftware.tasktracker.project.Project;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor
public class Board {

    @Id
    @GeneratedValue
    Long id;

    @ManyToOne
    @JoinColumn(name="project_id", nullable = false)
    Project project;


}

package bestsoftware.tasktracker;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Project {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    Long id;


    String name;

    @ManyToMany
    List<User> users;

    @OneToMany
    List<Board> boards;
}

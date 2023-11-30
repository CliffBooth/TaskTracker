package bestsoftware.tasktracker.user;

import bestsoftware.tasktracker.board.Board;
import bestsoftware.tasktracker.project.Project;
import bestsoftware.tasktracker.task.Task;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

//can't use data, because it can't construct toString() because of the circular reference.
@Setter
@Getter
@EqualsAndHashCode
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor
@Builder
@Table(name="_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue
    Long Id;

    @Column(unique = true)
    String name;

    @Column
    String password;

    @ManyToMany(mappedBy = "users")
    List<Project> projects = new ArrayList<>();

    @ManyToMany(mappedBy = "owner")
    List<Project> ownedProjects = new ArrayList<>();

    @ManyToMany(mappedBy = "users")
    List<Task> tasks = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

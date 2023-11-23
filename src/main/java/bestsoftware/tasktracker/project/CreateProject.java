package bestsoftware.tasktracker.project;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateProject {
    @NotBlank(message = "[name] must not be blank")
    private String name;
}

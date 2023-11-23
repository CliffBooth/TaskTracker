package bestsoftware.tasktracker.project;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProject {
    @NotBlank(message = "[name] must not be blank")
    String name;

    @NotNull(message = "[memberNames] must not be null")
    List<String> memberNames;

    @NotEmpty(message = "[ownerNames] must not be empty")
    List<String> ownerNames;

    @NotNull(message = "[boardIds] must not be null")
    List<Long> boardIds;
}

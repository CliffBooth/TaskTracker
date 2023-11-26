package bestsoftware.tasktracker.board;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddNewTask {

    @NotBlank(message = "[title] must not be blank")
    String text;
}

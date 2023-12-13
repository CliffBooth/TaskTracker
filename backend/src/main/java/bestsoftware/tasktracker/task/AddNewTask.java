package bestsoftware.tasktracker.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddNewTask {

    @NotNull(message = "[boardId] must not be blank")
    Long boardId;

    @NotBlank(message = "[text] must not be blank")
    String text;
}

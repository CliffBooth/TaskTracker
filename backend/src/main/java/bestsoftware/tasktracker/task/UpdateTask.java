package bestsoftware.tasktracker.task;

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
public class UpdateTask {
    @NotBlank(message = "[text] must not be blank")
    String text;

    @NotNull(message = "[userIds] must not be null")
    List<Long> userIds;

    @NotNull(message = "[boardId] must not be null")
    Long boardId;
}

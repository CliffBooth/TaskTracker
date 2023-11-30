package bestsoftware.tasktracker.board;

import jakarta.validation.constraints.NotBlank;
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
public class CreateBoard {
    @NotBlank(message = "[title] must not be blank")
    private String title;

    @NotNull(message = "[projectId] must not be null")
    private Long projectId;
}

package gb.demochkin.ecommerce.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    @NotNull
    private String login;
    @NotNull(message = "Email should not be empty")
    @Email
    private String email;
    @NotNull(message = "Password should not be empty")
    private String password;
}

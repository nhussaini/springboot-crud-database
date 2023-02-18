package net.javaguides.springboot.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    //make sure not to include sensitive information in dto
    //because rest api should not send sensitive information to client
    private Long id;
    //User first name should not be null or empty
    @NotEmpty
    private String firstName;

    //User last name should not be null or empty
    @NotEmpty
    private String lastName;

    //User email should not be null or empty
    //Email should be valid
    @NotEmpty
    @Email
    private String email;
}

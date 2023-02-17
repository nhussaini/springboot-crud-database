package net.javaguides.springboot.dto;

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
    private String firstName;
    private String lastName;
    private String email;
}

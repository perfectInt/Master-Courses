package io.sultanov.mastercourses.api.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UserDTO {

    @NotBlank
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    @Size(min = 8)
    private String password;

    @Size(min = 8)
    private String checkPassword;
}

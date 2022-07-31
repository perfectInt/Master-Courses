package io.sultanov.mastercourses.api.dtos;

import io.sultanov.mastercourses.enums.UserRole;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
public class ChangeRoleDTO {

    @NotNull
    private Set<String> roles;
}

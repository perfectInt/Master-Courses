package io.sultanov.mastercourses.api.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ChangeRoleDTO {

    @NotBlank
    private String role;
}
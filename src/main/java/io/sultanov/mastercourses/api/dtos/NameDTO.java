package io.sultanov.mastercourses.api.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class NameDTO {

    @NotBlank
    private String name;
}

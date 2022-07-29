package io.sultanov.mastercourses.api.dtos;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AccessDTO {

    @NotNull
    private Boolean access;
}

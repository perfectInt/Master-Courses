package io.sultanov.mastercourses.exceptions.users;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "You can't change admin role")
public class ChangeAdminRoleException extends RuntimeException{
}

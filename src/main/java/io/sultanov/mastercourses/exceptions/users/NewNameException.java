package io.sultanov.mastercourses.exceptions.users;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "names must be different!!")
public class NewNameException extends RuntimeException{
}

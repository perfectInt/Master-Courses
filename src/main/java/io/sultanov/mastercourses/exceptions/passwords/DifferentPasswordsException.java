package io.sultanov.mastercourses.exceptions.passwords;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "New and repeat password must be equal!")
public class DifferentPasswordsException extends RuntimeException{
}

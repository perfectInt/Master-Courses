package io.sultanov.mastercourses.exceptions.passwords;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Old and new password must be different!")
public class OldNewPasswordException extends RuntimeException{
}

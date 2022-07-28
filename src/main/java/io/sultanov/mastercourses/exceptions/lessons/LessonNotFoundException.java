package io.sultanov.mastercourses.exceptions.lessons;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Lesson is not found!")
public class LessonNotFoundException extends RuntimeException{
}

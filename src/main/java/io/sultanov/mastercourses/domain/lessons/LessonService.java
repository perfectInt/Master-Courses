package io.sultanov.mastercourses.domain.lessons;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;

    public List<Lesson> getLessons() {
        return lessonRepository.findByOrderByNumber();
    }

    public Lesson createLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    public Lesson getLesson(Long number) {
        return lessonRepository.findByNumber(number).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}

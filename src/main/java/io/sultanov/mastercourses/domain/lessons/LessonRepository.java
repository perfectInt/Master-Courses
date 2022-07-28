package io.sultanov.mastercourses.domain.lessons;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    List<Lesson> findByOrderByNumber();

    Optional<Lesson> findByNumber(Long number);
}

package org.zerhusen.Repositories.PauRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.zerhusen.Entities.PauEntities.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Integer>, PagingAndSortingRepository<Lesson, Integer> {
}

package org.zerhusen.Repositories.PauRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.zerhusen.Entities.PauEntities.Chapter;

public interface ChapterRepository extends JpaRepository<Chapter, Integer>, PagingAndSortingRepository<Chapter,Integer> {
}

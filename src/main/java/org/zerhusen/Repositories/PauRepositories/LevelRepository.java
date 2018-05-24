package org.zerhusen.Repositories.PauRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.zerhusen.Entities.PauEntities.Level;

public interface LevelRepository extends JpaRepository<Level, Integer>, PagingAndSortingRepository<Level, Integer> {
}

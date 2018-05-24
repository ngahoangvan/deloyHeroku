package org.zerhusen.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerhusen.Entities.Title;

public interface TitleRepository extends JpaRepository<Title,Integer> {
}

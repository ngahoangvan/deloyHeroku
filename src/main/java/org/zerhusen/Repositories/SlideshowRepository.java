package org.zerhusen.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerhusen.Entities.Slideshow;

public interface SlideshowRepository extends JpaRepository<Slideshow, Integer> {
}

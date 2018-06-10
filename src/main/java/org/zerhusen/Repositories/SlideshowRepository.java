package org.zerhusen.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zerhusen.Entities.Slideshow;

public interface SlideshowRepository extends JpaRepository<Slideshow, Integer> {

    @Query(value = "SELECT image FROM slideshow where id_slideshow = :idSlideshow", nativeQuery = true)
    String findImageSlideShow(@Param("idSlideshow")Integer idTrainer);
}

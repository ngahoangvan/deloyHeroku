package org.zerhusen.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerhusen.Entities.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer>{
    
}

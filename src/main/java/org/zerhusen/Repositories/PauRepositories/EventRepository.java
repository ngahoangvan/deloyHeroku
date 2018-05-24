package org.zerhusen.Repositories.PauRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.zerhusen.Entities.PauEntities.Events;

public interface EventRepository extends JpaRepository<Events,Integer>, PagingAndSortingRepository<Events,Integer> {

    @Query(value = "select count(id_event) from events",nativeQuery = true)
    Long countEvents();

    @Query(value = "SELECT *\n" +
        "FROM mydb.events\n" +
        "where month(create_at) = :month", nativeQuery = true)
    Iterable<Events> getEventMonth(@Param("month") int month);
}

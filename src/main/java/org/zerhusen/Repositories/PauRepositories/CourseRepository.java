package org.zerhusen.Repositories.PauRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.zerhusen.Entities.PauEntities.Course;


public interface CourseRepository extends JpaRepository<Course,Integer>,PagingAndSortingRepository<Course,Integer> {
    @Query(value = "select count(id_course) from course",nativeQuery = true)
    Long countCourse();

}

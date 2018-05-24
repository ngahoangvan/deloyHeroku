package org.zerhusen.Repositories.PauRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.zerhusen.Entities.PauEntities.GroupCategory;

import java.util.List;

@Repository
public interface GroupCategoryRepository extends CrudRepository<GroupCategory, Integer>,PagingAndSortingRepository<GroupCategory,Integer> {
    List<GroupCategory> findAllByCategory(String q);

}

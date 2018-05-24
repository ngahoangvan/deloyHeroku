package org.zerhusen.Repositories.PauRepositories;

import edu.umd.cs.findbugs.ba.bcp.New;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.zerhusen.Entities.PauEntities.News;

public interface NewsRepository extends CrudRepository<News, Integer>, PagingAndSortingRepository<News, Integer> {

    @Query(value = "select count(id_news) from news",nativeQuery = true)
    Long countNews();
}

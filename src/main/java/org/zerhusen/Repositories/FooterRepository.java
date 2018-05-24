package org.zerhusen.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.zerhusen.Entities.Footer;

@Repository
public interface FooterRepository extends CrudRepository<Footer, Integer> {
    Footer findAllByText(String q);
}

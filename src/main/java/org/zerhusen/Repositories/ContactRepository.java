package org.zerhusen.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerhusen.Entities.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer>{
}

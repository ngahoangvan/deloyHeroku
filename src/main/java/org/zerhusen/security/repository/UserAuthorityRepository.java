package org.zerhusen.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zerhusen.model.security.UserAuthority;

public interface UserAuthorityRepository extends JpaRepository<UserAuthority, Long> {
}

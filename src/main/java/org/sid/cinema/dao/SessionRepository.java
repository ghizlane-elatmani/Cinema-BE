package org.sid.cinema.dao;

import org.sid.cinema.dao.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SessionRepository extends JpaRepository<Session, Long> {
}

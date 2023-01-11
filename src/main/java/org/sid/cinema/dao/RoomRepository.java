package org.sid.cinema.dao;

import org.sid.cinema.dao.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RoomRepository extends JpaRepository<Room, Long> {
}

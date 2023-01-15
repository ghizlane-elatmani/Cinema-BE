package org.sid.cinema;

import org.sid.cinema.dao.entities.Movie;
import org.sid.cinema.service.ICinemaInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CinemaApplication implements CommandLineRunner {

	@Autowired
	private ICinemaInitService cinemaInitService;
	@Autowired
	private RepositoryRestConfiguration restConfiguration;

	public static void main(String[] args) {
		SpringApplication.run(CinemaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		restConfiguration.exposeIdsFor(Movie.class);
		cinemaInitService.initCities();
		cinemaInitService.initCinemas();
		cinemaInitService.initRooms();
		cinemaInitService.initPlaces();
		cinemaInitService.initSessions();
		cinemaInitService.initCategories();
		cinemaInitService.initMovies();
		cinemaInitService.initProjections();
		cinemaInitService.initTickets();
	}
}

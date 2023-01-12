package org.sid.cinema.service;

import jakarta.transaction.Transactional;
import org.sid.cinema.dao.*;
import org.sid.cinema.dao.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Service
@Transactional
public class CinemaInitServiceImpl implements  ICinemaInitService{

    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private ProjectionRepository projectionRepository;
    @Autowired
    private TicketRepository ticketRepository;


    @Override
    public void initCities() {
        Stream.of("Casablanca", "Marrakech", "Rabat", "Tanger").forEach( name -> {
            City city = new City();
            city.setName(name);
            cityRepository.save(city);
        });
    }

    @Override
    public void initCinemas() {
        cityRepository.findAll().forEach( c -> {
            Stream.of("MEGARAMA", "IMAX", "FOUNOUN", "CHAHRAZAD", "DAOULIZ")
                    .forEach(nameCinema ->{
                Cinema cinema = new Cinema();
                cinema.setName(nameCinema);
                cinema.setRoomsNumber(3 + (int) (Math.random() * 7));
                cinema.setCity(c);
                cinemaRepository.save(cinema);
            });
        });
    }

    @Override
    public void initRooms() {
        cinemaRepository.findAll().forEach( cinema -> {
            for(int i = 0; i < cinema.getRoomsNumber(); i++){
                Room room = new Room();
                room.setName("Room " + i+1);
                room.setCinema(cinema);
                room.setPlaceNumber(15 + (int) (Math.random() * 20));
                roomRepository.save(room);
            }
        });
    }

    @Override
    public void initPlaces() {
        roomRepository.findAll().forEach( room -> {
            for (int i = 0; i < room.getPlaceNumber(); i++) {
                Place place = new Place();
                place.setNumber(i + 1);
                place.setRoom(room);
                placeRepository.save(place);
            }
        });
    }

    @Override
    public void initSessions() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Stream.of("12:00", "15:00", "17:00", "19:00", "21:00").forEach( s -> {
            Session session = new Session();
            try {
                session.setStartTime(dateFormat.parse(s));
                sessionRepository.save(session);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public void initCategories() {
        Stream.of("Historic", "Action", "Fiction", "Drama").forEach( cat -> {
            Category category = new Category();
            category.setName(cat);
            categoryRepository.save(category);
        });
    }

    @Override
    public void initMovies() {
        double[] time = new double[]{1, 1.5, 2, 2.5, 3};
        List<Category> categories = categoryRepository.findAll();
        Stream.of("Inception", "IronMan", "SpiderMan", "Titanic").forEach( mov -> {
            Movie movie = new Movie();
            movie.setTitle(mov);
            movie.setDuration(time[new Random().nextInt(time.length)]);
            movie.setPhoto(mov);
            movie.setCategory(categories.get(new Random().nextInt(categories.size())));
            movieRepository.save(movie);
        });
    }



    @Override
    public void initProjections() {
        double[] prices = new double[]{30, 50, 60, 70, 90, 100};
        cityRepository.findAll().forEach( city -> {
            city.getCinemas().forEach( cinema -> {
                cinema.getRooms().forEach( room ->  {
                    movieRepository.findAll().forEach(movie -> {
                        sessionRepository.findAll().forEach(session -> {
                            Projection projection = new Projection();
                            projection.setMovie(movie);
                            projection.setDateProjection(new Date());
                            projection.setPrice(prices[new Random().nextInt(prices.length)]);
                            projection.setRoom(room);
                            projection.setSession(session);
                            projectionRepository.save(projection);
                        });
                    });
                });
            });
        });
    }

    @Override
    public void initTickets() {
        projectionRepository.findAll().forEach(p -> {
            p.getRoom().getPlaces().forEach(place -> {
                Ticket ticket = new Ticket();
                ticket.setPlace(place);
                ticket.setPrice(p.getPrice());
                ticket.setProjection(p);
                ticket.setReserved(false);
                ticketRepository.save(ticket);
            });
        });
    }
}

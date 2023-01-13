package org.sid.cinema.web;

import org.sid.cinema.dao.MovieRepository;
import org.sid.cinema.dao.entities.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class CinemaRestController {

    @Autowired
    MovieRepository movieRepository;

    @GetMapping(path = "/movieImage/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] image(@PathVariable(name = "id") Long id) throws IOException {
        Movie movie = movieRepository.findById(id).get();
        String photoName = movie.getPhoto();
        File file = new File(System.getProperty("user.home") + "/" + "photo" +"/" + movie.getPhoto());
        Path path = Paths.get(file.toURI());
        //Path p = Paths.get(System.getProperty("user:home"), "photo", movie.getPhoto());
        return Files.readAllBytes(path);
    }

}

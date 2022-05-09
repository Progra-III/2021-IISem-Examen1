package controller;

import model.Cinema;
import model.Movie;
import utilities.Utilities;
import view.MoviesWindow;

import java.util.ArrayList;
import java.util.List;

public class MoviesController {

    public boolean addMovie(Movie movie) {
        Cinema cinema = PrincipalController.getInstance().getCinema();
        if (searchMovie(movie.getId()) == null) {
            cinema.getMovies().add(movie);
            return true;
        }
        return false;
    }

    public Movie searchMovie(int id) {
        Cinema cinema = PrincipalController.getInstance().getCinema();
        for (Movie movie : cinema.getMovies()) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }


    public ArrayList<Movie> searchStartsWith(String idStartsWith) {
        Cinema cinema = PrincipalController.getInstance().getCinema();

        ArrayList<Movie> movies = new ArrayList<>();
        String idStr;

        for (Movie movie : cinema.getMovies()) {
            idStr = String.valueOf(movie.getId());

            if (idStr.startsWith(idStartsWith)) {
                movies.add(movie);
            }
        }
        return movies;
    }

    public int getMoviePos(int id) {

        Cinema cinema = PrincipalController.getInstance().getCinema();
        List<Movie> movies = cinema.getMovies();

        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    public static MoviesController getInstance() {
        if (instance == null) {
            return new MoviesController();
        }
        return instance;
    }

    private static MoviesController instance;

}

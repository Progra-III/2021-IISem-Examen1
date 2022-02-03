package controller;

import model.Cinema;
import model.Movie;
import utilities.Utilities;
import view.MoviesWindow;

public class MoviesController {
    //-------------------------------
    private static MoviesController instance;
    private MoviesWindow cinemaWindow;
    private Utilities utilities;

    //-------------------------------

    public MoviesController() {

        this.cinemaWindow = new MoviesWindow();
        this.cinemaWindow.setVisible(true);
    }

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

    public static MoviesController getInstance() {
        if (instance == null) {
            return new MoviesController();
        }
        return instance;
    }


}

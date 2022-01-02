package model;

import java.util.List;
import java.util.ArrayList;

public class Cinema {


    public Cinema(){
        this.actors = new ArrayList<Actor>();
        this.directors = new ArrayList<Director>();
        this.movies = new ArrayList<Movie>();

    }

    private List<Actor> actors;
    private List <Director> directors;
    private List<Movie> movies;


    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public List<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Director> directors) {
        this.directors = directors;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}

package model;

import java.util.List;
import java.util.ArrayList;

public class Movie {


    private int id;
    private String name;
    Director director;
    List<Actor> actors;


    public Movie(int id, String name, Director director, List<Actor> actors) {
        this.id = id;
        this.name = name;
        this.director = director;
        this.actors = actors;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }
}

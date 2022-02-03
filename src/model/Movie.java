package model;

import java.util.List;
import java.util.ArrayList;

public class Movie {

    public Movie(int id, String name, Director director, Actor actor) {
        this.id = id;
        this.name = name;
        this.director = director;
        this.actor = actor;
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

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    private int id;
    private String name;
    Director director;
    Actor actor;
}

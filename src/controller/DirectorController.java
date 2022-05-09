package controller;

import model.Actor;
import model.Cinema;
import model.Director;
import utilities.Utilities;
import view.DirectorWindow;
import view.PrincipalWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DirectorController {

    public static DirectorController getInstance() {
        if (instance == null) {
            instance = new DirectorController();
        }
        return instance;
    }

    public Boolean addDirector(Director director) {
        if (director == null) {
            return false;
        } else {
            Cinema cinema = PrincipalController.getInstance().getCinema();
            cinema.getDirectors().add(director);
            return true;
        }
    }

    public Director searchDirector(int id) {
        Cinema cinema = PrincipalController.getInstance().getCinema();

        if (!cinema.getDirectors().isEmpty()) {
            List<Director> directorList = cinema.getDirectors();

            for (Director director : directorList) {
                if (director.getId() == id) {
                    return director;
                }
            }
            return null;
        }
        return null;
    }

    public Director getDirectorByName(String name) {
        Cinema cinema = PrincipalController.getInstance().getCinema();

        for (Director director : cinema.getDirectors()) {
            if (director.getName() == name) {
                return director;
            }
        }
        return null;
    }

    public int getDirectorPos(int id) {

        Cinema cinema = PrincipalController.getInstance().getCinema();

        for (int i = 0; i < cinema.getDirectors().size(); i++) {
            if (id == cinema.getDirectors().get(i).getId()) {
                return i;
            }
        }
        return -1;
    }

    private static DirectorController instance;
}

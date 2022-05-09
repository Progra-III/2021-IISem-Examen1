package controller;

import model.Actor;
import model.Cinema;
import utilities.Utilities;
import view.ActorWindow;
import view.PrincipalWindow;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActorController {
    //-------------------------------


    //-------------------------------

    //Will add the received Actor
    public Boolean addActor(Actor actor) {
        if (actor == null) {
            return false;
        } else {
            Cinema cinema = PrincipalController.getInstance().getCinema();
            cinema.getActors().add(actor);
            return true;
        }
    }

    public Actor getActorByName(String name) {
        Cinema cinema = PrincipalController.getInstance().getCinema();

        for (Actor actor : cinema.getActors()) {
            if (actor.getName() == name) {
                return actor;
            }
        }
        return null;
    }

    public int getActorPos(int id) {

        Cinema cinema = PrincipalController.getInstance().getCinema();

        for (int i = 0; i < cinema.getActors().size(); i++) {
            if (id == cinema.getActors().get(i).getId()) {
                return i;
            }
        }
        return -1;
    }

    public Actor searchActor(int id) {

        Cinema cinema = PrincipalController.getInstance().getCinema();

        for (int i = 0; i < cinema.getActors().size(); i++) {
            if (id == cinema.getActors().get(i).getId()) {
                return cinema.getActors().get(i);
            }
        }
        return null;
    }


    public static ActorController getInstance() {

        if (instance == null) {
            instance = new ActorController();
        }
        return instance;
    }

    private static ActorController instance;

}

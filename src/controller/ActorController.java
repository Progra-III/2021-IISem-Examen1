package controller;

import model.Actor;
import model.Cinema;
import utilities.Utilities;
import view.ActorWindow;
import view.PrincipalWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActorController {
    //-------------------------------
    private Utilities utilities;

    //-------------------------------

    public Boolean addActor(Actor actor){
        if(actor == null){
            return false;
        }else{
            Cinema cinema = PrincipalController.getInstance().getCinema();
            cinema.getActors().add(actor);
            return true;
        }
    }


    public static ActorController getInstance(){

        if(instance == null){
            instance = new ActorController();
        }
        return instance;
    }

    private static ActorController instance;

}

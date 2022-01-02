package controller;

import model.Cinema;
import model.Director;
import utilities.Utilities;
import view.DirectorWindow;
import view.PrincipalWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DirectorController {

    public static DirectorController getInstance(){
        if(instance == null){
            instance = new DirectorController();
        }
        return instance;
    }

    public Boolean addDirector(Director director){
        if(director == null){
            return false;
        }else{
            Cinema cinema = PrincipalController.getInstance().getCinema();
            cinema.getDirectors().add(director);
            return true;
        }
    }



    private static DirectorController instance;
}

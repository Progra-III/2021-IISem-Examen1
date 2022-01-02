package controller;

import model.Cinema;
import view.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrincipalController {

    //-------------------------------
    private Cinema cinema;


    //-------------------------------

    public PrincipalController() {
        this.cinema = new Cinema();
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public static PrincipalController getInstance(){
        if(instance == null){
            instance = new PrincipalController();
        }
        return instance;
    }


    private static PrincipalController instance;
}

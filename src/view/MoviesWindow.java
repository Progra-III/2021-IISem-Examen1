package view;

import controller.ActorController;
import controller.DirectorController;
import model.Movie;
import controller.MoviesController;
import controller.PrincipalController;
import model.Actor;
import model.Director;
import utilities.Utilities;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MoviesWindow extends JFrame {
    private JPanel windowPanel;
    private JTextField txtId;
    private JTable dataTable;
    private JTextField txtName;
    private JComboBox comboDirector;
    private JComboBox comboActor;
    private JButton btnAdd;
    private JButton btnSearch;
    private JButton btnList;
    private JButton btnUpdate;
    private JButton btnExit;

    Utilities utilities = new Utilities();

    public MoviesWindow() {

        setContentPane(windowPanel);
        setTitle("Administrador de Peliculas");
        setSize(900, 500);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setResizable(false);
        setLocationRelativeTo(null);

        init();
    }

    public void createTable() {
        Object data[][] = {
        };
        dataTable.setModel(new DefaultTableModel(
                data,
                new String[]{"Id", "Nombre", "Director", "Actor Principal"}
        ));
    }

    public void init() {
        createTable();

        AddButtonListener addListener = new AddButtonListener();
        btnAdd.addActionListener(addListener);

        SearchButtonListener searchListener = new SearchButtonListener();
        btnSearch.addActionListener(searchListener);

        ListButtonListener listButtonListener = new ListButtonListener();
        btnList.addActionListener(listButtonListener);

        UpdateButtonListener updateButtonListener = new UpdateButtonListener();
        btnUpdate.addActionListener(updateButtonListener);

        ExitButtonListener exitListener = new ExitButtonListener();
        btnExit.addActionListener(exitListener);

        //Will fill the Actors Combobox
        List<Actor> actorList = PrincipalController.getInstance().getCinema().getActors();
        DefaultComboBoxModel actorModel = (DefaultComboBoxModel) comboActor.getModel();
        for (int i = 0; i < actorList.size(); i++) {
            actorModel.addElement(actorList.get(i).getName());
        }
        comboActor.setModel(actorModel);

        //Will fill the Directors Combobox
        List<Director> directorList = PrincipalController.getInstance().getCinema().getDirectors();
        DefaultComboBoxModel directorsModel = (DefaultComboBoxModel) comboDirector.getModel();

        for (int i = 0; i < directorList.size(); i++) {
            directorsModel.addElement(directorList.get(i).getName());
        }
        comboDirector.setModel(directorsModel);

    }

    public void displayErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(windowPanel, errorMessage, " WARNING ERROR", JOptionPane.ERROR_MESSAGE);
    }

    public void displayMessage(String message) {
        JOptionPane.showMessageDialog(windowPanel, message);
    }

    private class AddButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (utilities.isNumeric(txtId.getText())) {

                DefaultTableModel model = (DefaultTableModel) dataTable.getModel();


                String actorName = comboActor.getSelectedItem().toString();
                String directorName = comboDirector.getSelectedItem().toString();

                if (ActorController.getInstance().getActorByName(actorName) != null) {
                    if (DirectorController.getInstance().getDirectorByName(directorName) != null) {
                        Actor actor = ActorController.getInstance().getActorByName(actorName);
                        Director director = DirectorController.getInstance().getDirectorByName(directorName);
                        int id = Integer.parseInt(txtId.getText());

                        Movie movie = new Movie(id, txtName.getText(), director, actor);

                        MoviesController.getInstance();

                        if (MoviesController.getInstance().addMovie(movie)) {

                            String[] row = {txtId.getText(), txtName.getText(), comboDirector.getSelectedItem().toString(), comboActor.getSelectedItem().toString()};
                            model.addRow(row);
                            dataTable.setModel(model);
                            displayMessage("Pelicula agregada con exito.");
                        } else {
                            displayErrorMessage("La pelicula no pudo agregarse.");
                        }
                    }
                }


            }
        }
    }

    private class SearchButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (utilities.isNumeric(txtId.getText())) {
                int id = Integer.parseInt(txtId.getText());

                if (MoviesController.getInstance().searchMovie(id) != null) {

                    Movie movie = MoviesController.getInstance().searchMovie(id);
                    displayMessage("ID: " + movie.getId() + "\n" + "Nombre: " + movie.getName() + "\n" + "Director: " + movie.getDirector().getName() + "\n" + movie.getActor().getName());

                } else {
                    displayErrorMessage("No existen coincidencias con este id");
                }
            }
        }
    }

    private class ListButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            if (utilities.isNumeric(txtId.getText())) {
                ArrayList<Movie> movies = MoviesController.getInstance().searchStartsWith(txtId.getText());

                if (!movies.isEmpty()) {
                    DefaultTableModel model = (DefaultTableModel) dataTable.getModel();

                    for (int i = 0; i < PrincipalController.getInstance().getCinema().getMovies().size(); i++) {
                        model.removeRow(0);
                    }

                    String[] movieStr;
                    for (Movie movie : movies) {
                        movieStr = new String[]{String.valueOf(movie.getId()), movie.getName(), movie.getDirector().getName(), movie.getActor().getName()};
                        model.addRow(movieStr);
                    }

                    dataTable.setModel(model);
                } else {
                    displayErrorMessage("No hay coincidencias.");
                }
            }
        }
    }

    private class UpdateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (utilities.isNumeric(txtId.getText())) {

                if (MoviesController.getInstance().searchMovie(Integer.parseInt(txtId.getText())) != null) {
                    Movie movie = MoviesController.getInstance().searchMovie(Integer.parseInt(txtId.getText()));

                    Director director = new Director();
                    Actor actor = new Actor();

                    movie.setName(txtName.getText());

                    String directorStr = comboDirector.getSelectedItem().toString();

                    if (DirectorController.getInstance().getDirectorByName(directorStr) != null) {
                        director = DirectorController.getInstance().getDirectorByName(directorStr);
                        movie.setDirector(director);
                    }

                    String actorStr = comboActor.getSelectedItem().toString();

                    if (ActorController.getInstance().getActorByName(actorStr) != null) {
                        actor = ActorController.getInstance().getActorByName(actorStr);
                        movie.setActor(actor);
                    }

                    DefaultTableModel model = (DefaultTableModel) dataTable.getModel();

                    int pos = MoviesController.getInstance().getMoviePos(Integer.parseInt(txtId.getText()));


                    model.setValueAt(movie.getName(), pos, 1);

                    if (actor.getName() != "") {
                        model.setValueAt(director.getName(), pos, 2);
                    }
                    if (actor.getName() != "") {
                        model.setValueAt(actor.getName(), pos, 3);
                    }


                } else {
                    displayErrorMessage("Esta pelicula no está registrada.");
                }


            } else {
                displayErrorMessage("Id invalido");
            }


        }
    }

    private class ExitButtonListener implements ActionListener {


        @Override
        public void actionPerformed(ActionEvent e) {
            PrincipalWindow principalWindow = new PrincipalWindow();
            dispose();
        }
    }


}

package view;

import controller.PrincipalController;
import model.Actor;
import model.Director;
import model.Movie;

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
                {14, "Good Fellas", 2.5, "Martin Scorcese", "Jeff Goldblum"},
                {},
                {}
        };
        dataTable.setModel(new DefaultTableModel(
                data,
                new String[]{"Id", "Nombre", "Duracion", "Director", "Actor Principal"}
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


        }
    }

    private class SearchButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class ListButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class UpdateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

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

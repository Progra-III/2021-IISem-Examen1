package view;

import controller.ActorController;
import controller.DirectorController;
import controller.PrincipalController;
import model.Director;
import utilities.Utilities;
import model.Actor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DirectorWindow extends JFrame {

    //-------------------------------
    private JTextField txtID;
    private JTextField txtName;
    private JTextField txtAwards;
    private JTable dataTable;
    private JButton btnAdd;
    private JButton btnSearch;
    private JButton btnList;
    private JButton btnUpdate;
    private JButton btnExit;
    private JPanel windowPanel;
    private JScrollPane scrollPane;
    Utilities utilities = new Utilities();

    //-------------------------------

    public DirectorWindow() {

        setContentPane(windowPanel);
        setTitle("Administrador de Directores");
        setSize(900, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        init();
    }

    public void init() {
        createTable();
        loadData();

        AddButtonListener addButtonListener = new AddButtonListener();
        btnAdd.addActionListener(addButtonListener);

        SearchButtonListener searchButtonListener = new SearchButtonListener();
        btnSearch.addActionListener(searchButtonListener);

        ListButtonListener listButtonListener = new ListButtonListener();
        btnList.addActionListener(listButtonListener);

        UpdateButtonListener updateButtonListener = new UpdateButtonListener();
        btnUpdate.addActionListener(updateButtonListener);

        ExitButtonListener exitButtonListener = new ExitButtonListener();
        btnExit.addActionListener(exitButtonListener);
    }

    public void createTable() {
        dataTable.setModel(new DefaultTableModel(
                null,
                new String[]{"Id", "Nombre", "Premios"}
        ));
    }

    public void loadData() {
        if (!PrincipalController.getInstance().getCinema().getDirectors().isEmpty()) {
            List<Director> directors = PrincipalController.getInstance().getCinema().getDirectors();
            DefaultTableModel directorsModel = (DefaultTableModel) dataTable.getModel();

            for (Director director : directors) {
                String[] directorStr = {String.valueOf(director.getId()), director.getName(), String.valueOf(director.getAwards())};
                directorsModel.addRow(directorStr);
            }

            dataTable.setModel(directorsModel);
        } else {
            System.out.println("Any data to load");
        }
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
            //Verify that input is numeric
            if (utilities.isNumeric(txtID.getText())) {
                if (utilities.isNumeric(txtAwards.getText())) {

                    //Convert data to valid values
                    String id = txtID.getText();
                    int id_numeric = Integer.parseInt(id);

                    String name = txtName.getText();

                    String awards = txtAwards.getText();
                    int awards_numeric = Integer.parseInt(awards);

                    //Create a new Actor in order to add it to the Actors list and the JTable
                    Director director = new Director(id_numeric, name, awards_numeric);
                    String[] directorStr = {id, name, awards};

                    //Verify if an Actor with this ID already exists
                    if (DirectorController.getInstance().searchDirector(id_numeric) == null) {
                        //Add the actor to the list and to the JTable
                        if (DirectorController.getInstance().addDirector(director)) {
                            DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
                            model.addRow(directorStr);
                            dataTable.setModel(model);

                            displayMessage("Director agregado con exito.");

                        } else {
                            //Error message if the actor is invalid
                            displayErrorMessage("Director invalido.");
                        }
                    } else {
                        displayErrorMessage("Ya existe un director con ese ID. Favor digitar uno distinto");
                    }
                }
            }
        }
    }

    private class SearchButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //Verify that input is numeric
            if (utilities.isNumeric(txtID.getText())) {

                //Convert data to valid values
                String id = txtID.getText();
                int id_numeric = Integer.parseInt(id);

                //Search for the Actor
                if (DirectorController.getInstance().searchDirector(id_numeric) != null) {

                    //Create an auxiliar actor that will be used to show its information
                    Director director = DirectorController.getInstance().searchDirector(id_numeric);
                    displayMessage("ID: " + director.getId() + "\n" + "Nombre: " + director.getName() + "\n" + "Cantidad de premios: " + director.getAwards());


                } else {
                    displayErrorMessage("Sin coincidencia.");
                }
            } else {
                displayErrorMessage("No hay valores numericos en ID o está vacío.");
            }
        }
    }

    private class ListButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //First of all, will verify that we have
            if (utilities.isNumeric((txtID.getText()))) {

                List<Director> directorsAux = PrincipalController.getInstance().getCinema().getDirectors();

                String id = txtID.getText();
                //The next line will save the current table model

                //Proceed to edit the current table model deleting all the current rows
                DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
                for (int i = 0; i < directorsAux.size(); i++) {
                    model.removeRow(0);
                }
                //Search for any coincidence in the Actors List
                for (Director aux : directorsAux) {
                    String str_id = String.valueOf(aux.getId());

                    //Verify if the current Actor id starts with the provided id
                    if (str_id.startsWith(id)) {
                        int director_id = aux.getId();
                        String director_name = aux.getName();
                        int director_awards = aux.getAwards();

                        String[] directorToAdd = {String.valueOf(director_id), director_name, String.valueOf(director_awards)};

                        model.addRow(directorToAdd);
                    }
                }
                //If there is any coincidence an error message will appear
                if (model.getRowCount() < 1) {
                    displayErrorMessage("No hay coincidencias para listar.");
                }
                //Finally the data model is edited
                else {
                    dataTable.setModel(model);
                }
            }
        }
    }

    private class UpdateButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //Verify
            if (utilities.isNumeric((txtID.getText()))) {
                String id = txtID.getText();
                int id_numeric = Integer.parseInt(id);


                if (DirectorController.getInstance().searchDirector(id_numeric) != null) {

                    Director director = DirectorController.getInstance().searchDirector(id_numeric);
                    int pos = DirectorController.getInstance().getDirectorPos(id_numeric);

                    DefaultTableModel model = (DefaultTableModel) dataTable.getModel();

                    if (txtName.getText() != "") {
                        model.setValueAt(txtName.getText(), pos, 1);
                        dataTable.setModel(model);
                    }
                    if (utilities.isNumeric(txtAwards.getText())) {
                        if (txtAwards.getText() != "") {
                            model.setValueAt(txtAwards.getText(), pos, 2);
                            dataTable.setModel(model);
                        }
                    } else {
                        displayErrorMessage("Valor de premios invalido. Debe ser numerico.");
                    }
                } else {
                    displayErrorMessage("Este id no coincide con ninguno de los existentes.");
                }
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

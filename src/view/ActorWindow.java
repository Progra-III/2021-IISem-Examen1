package view;

import controller.ActorController;
import controller.PrincipalController;
import model.Actor;
import utilities.Utilities;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import java.util.List;

public class ActorWindow extends JFrame {

    //-------------------------------
    private JTextField txtID;
    private JTextField txtName;
    private JTextField txtAwards;
    private JTable dataTable;
    private JScrollPane scrollPane;

    //Buttons
    private JButton btnAdd;
    private JButton btnSearch;
    private JButton btnList;
    private JButton btnUpdate;
    private JButton btnExit;
    private JPanel windowPanel;

    Utilities utilities = new Utilities();

    //-------------------------------

    public ActorWindow() {

        setContentPane(windowPanel);
        setTitle("Administrador Actores");
        setSize(900, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        init();
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

    }

    public void createTable() {
        dataTable.setModel(new DefaultTableModel(
                null,
                new String[]{"Id", "Nombre", "Premios"}
        ));


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
                    Actor actor = new Actor(id_numeric, name, awards_numeric);
                    String[] actorString = {id, name, awards};


                    //Verify if an Actor with this ID already exists
                    if (ActorController.getInstance().searchActor(id_numeric) == null) {
                        //Add the actor to the list and to the JTable
                        if (ActorController.getInstance().addActor(actor)) {
                            DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
                            model.addRow(actorString);
                            dataTable.setModel(model);

                            displayMessage("Actor agregado con exito.");

                        } else {
                            //Error message if the actor is invalid
                            displayErrorMessage("Actor invalido.");
                        }
                    } else {
                        displayErrorMessage("Ya existe un actor con ese ID. Favor digitar uno distinto");
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
                if (ActorController.getInstance().searchActor(id_numeric) != null) {

                    //Create an auxiliar actor that will be used to show its information
                    Actor actor = ActorController.getInstance().searchActor(id_numeric);
                    displayMessage("ID: " + actor.getId() + "\n" + "Nombre: " + actor.getName() + "\n" + "Cantidad de premios: " + actor.getAwards());


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

                List<Actor> actorsAux = PrincipalController.getInstance().getCinema().getActors();

                String id = txtID.getText();
                //The next line will save the current table model

                //Proceed to edit the current table model deleting all the current rows
                DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
                for (int i = 0; i < actorsAux.size(); i++) {
                    model.removeRow(0);
                }

                //Search for any coincidence in the Actors List


                for (Actor aux : actorsAux) {
                    String str_id = String.valueOf(aux.getId());

                    //Verify if the current Actor id starts with the provided id
                    if (str_id.startsWith(id)) {
                        int actor_id = aux.getId();
                        String actor_name = aux.getName();
                        int actor_awards = aux.getAwards();

                        String[] actorToAdd = {String.valueOf(actor_id), actor_name, String.valueOf(actor_awards)};

                        model.addRow(actorToAdd);
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


                if (ActorController.getInstance().searchActor(id_numeric) != null) {

                    Actor actor = ActorController.getInstance().searchActor(id_numeric);
                    int pos = ActorController.getInstance().getActorPos(id_numeric);

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

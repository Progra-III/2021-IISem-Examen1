package view;

import controller.ActorController;
import controller.PrincipalController;
import model.Actor;
import utilities.Utilities;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ActorWindow extends JFrame{

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

    public ActorWindow(){

        setContentPane(windowPanel);
        setTitle("Administrador Actores");
        setSize(900,500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
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


    public String getTxtID() {
        return txtID.getText();
    }

    public String getTxtName() {
        return txtName.getText();
    }

    public String getTxtAwards() {
        return txtAwards.getText();
    }

    public void createTable(){
        Object data[][] = {

        };
        dataTable.setModel(new DefaultTableModel(
                data,
                new String[]{"Id","Nombre","Premios"}
        ));
    }

    public void displayErrorMessage(String errorMessage){
        JOptionPane.showMessageDialog(windowPanel, errorMessage, " WARNING ERROR", JOptionPane.ERROR_MESSAGE);
    }
<
    public void displayMessage(String message){
        JOptionPane.showMessageDialog(windowPanel, message);
    }

    private class AddButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if(utilities.isNumeric(txtID.getText())) {
                if (utilities.isNumeric(txtAwards.getText())) {

                    String id = txtID.getText();
                    int id_numeric = Integer.parseInt(id);

                    String name = txtName.getText();

                    String awards = txtAwards.getText();
                    int awards_numeric = Integer.parseInt(awards);


                    Actor actor = new Actor(id_numeric, name, awards_numeric);
                    String actorString[] = {id, name, awards};

                    if (ActorController.getInstance().addActor(actor)) {


                        DefaultTableModel model = (DefaultTableModel) dataTable.getModel();
                        model.addRow(actorString);
                        dataTable.setModel(model);

                        displayMessage("Actor agregado con exito.");

                    } else {
                        displayErrorMessage("Actor invalido.");
                    }
                }
            }
        }
    }

    private class SearchButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {



            if(utilities.isNumeric(txtID.getText())){

                String id = txtID.getText();
                int id_numeric = Integer.parseInt(id);

                if(ActorController.getInstance().searchActor(id_numeric) != null){

                    Actor actor = ActorController.getInstance().searchActor(id_numeric);

                    displayMessage("ID: " + actor.getId() + "\n" + "Nombre: "+actor.getName() + "\n" + "Cantidad de premios: " + actor.getAwards());


                }

            }

        }
    }

    private class ListButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
                //First of all, will verify that we have
                if (utilities.isNumeric((txtID.getText()))) {

                    List<Actor> actorsAux = (ArrayList) PrincipalController.getInstance().getCinema().getActors();

                    String id = txtID.getText();
                    //The next line will save the current table model

                    //Proceed to edit the current table model
                    DefaultTableModel model = (DefaultTableModel) dataTable.getModel();

                    for (int i = 0; i < actorsAux.size(); i++) {
                        model.removeRow(0);
                    }


                    for (int i = 0; i < actorsAux.size(); i++) {

                        String str_id = String.valueOf(actorsAux.get(i).getId());

                        if (str_id.startsWith(id)) {
                            int actor_id = actorsAux.get(i).getId();
                            String actor_name = actorsAux.get(i).getName();
                            int actor_awards = actorsAux.get(i).getAwards();

                            String actorToAdd[] = {String.valueOf(actor_id), actor_name, String.valueOf(actor_awards)};

                            model.addRow(actorToAdd);
                        }
                    }

                    //Finally the data model edited

                    if (model.getRowCount() < 1) {
                        displayErrorMessage("No hay coincidencias para listar.");
                        //dataTable.setModel(model);
                    } else {
                        dataTable.setModel(model);
                    }
                }
        }
    }

    private class UpdateButtonListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class ExitButtonListener implements ActionListener{


        @Override
        public void actionPerformed(ActionEvent e) {
            PrincipalWindow principalWindow = new PrincipalWindow();
            dispose();
        }
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.skpcorp.crud;

import com.skpcorp.crud.data.AppQuery;
import com.skpcorp.crud.model.Student;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;

public class StudentController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showStudents();
        btnUpdate.setDisable(true);
        btnDelete.setDisable(false);
       fieldSearch.textProperty().addListener((ObservableList,oldValue,newValue) -> {
           filterData(newValue);
       });
    }

    @FXML
    public TextField fieldFirstname;

    @FXML
    public TextField fieldMiddlename;

    @FXML
    public TextField fieldLastname;
    
    @FXML
    public TextField fieldSearch;

    @FXML
    public Button btnNew;

    @FXML
    public Button btnSave;

    @FXML
    public Button btnUpdate;

    @FXML
    public Button btnDelete;

    @FXML
    public TableView<Student> tableView;

    @FXML
    public TableColumn<Student, Integer> colid;

    @FXML
    public TableColumn<Student, String> colFirstname;

    @FXML
    public TableColumn<Student, String> colMiddlename;

    @FXML
    public TableColumn<Student, String> colLastname;

    private Student student;

    @FXML
    public void addStudent() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Add confirmation");
        dialog.setHeaderText(" Are you sure you want to add a new student?");
        dialog.initModality(Modality.APPLICATION_MODAL);
        Label label = new Label("Name:" + fieldFirstname.getText() + "" + fieldLastname.getText());

        dialog.getDialogPane().setContent(label);
        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == okButton) {

            com.skpcorp.crud.model.Student student = new com.skpcorp.crud.model.Student(fieldFirstname.getText(), fieldMiddlename.getText(), fieldLastname.getText());
            com.skpcorp.crud.data.AppQuery query = new AppQuery();
            query.addStudent(student);
            showStudents();
        }

    }

    @FXML
    public void showStudents() {
        com.skpcorp.crud.data.AppQuery query = new com.skpcorp.crud.data.AppQuery();
        ObservableList<Student> list = query.getStudentList();
        colid.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
        colFirstname.setCellValueFactory(new PropertyValueFactory<Student, String>("firstname"));
        colMiddlename.setCellValueFactory(new PropertyValueFactory<Student, String>("middlename"));
        colLastname.setCellValueFactory(new PropertyValueFactory<Student, String>("lastname"));
        tableView.setItems(list);
    }

    @FXML

    public void mouseClicked(MouseEvent e) {
        try {
            Student student = tableView.getSelectionModel().getSelectedItem();
            student = new Student(student.getId(), student.getFirstname(), student.getMiddlename(), student.getLastname());
            this.student = student;
            fieldFirstname.setText(student.getFirstname());
            fieldMiddlename.setText(student.getMiddlename());
            fieldLastname.setText(student.getLastname());

            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
            btnNew.setDisable(true);
            btnSave.setDisable(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void updateStudent() {
        try {

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Update confirmation");
            dialog.setHeaderText(" Are you sure you want to update this student?");
            dialog.initModality(Modality.APPLICATION_MODAL);
            Label label = new Label("Name:" + fieldFirstname.getText() + "" + fieldLastname.getText());

            dialog.getDialogPane().setContent(label);
            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);

            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == okButton) {
                com.skpcorp.crud.data.AppQuery query = new com.skpcorp.crud.data.AppQuery();
                com.skpcorp.crud.model.Student student = new com.skpcorp.crud.model.Student(this.student.getId(), fieldFirstname.getText(), fieldMiddlename.getText(), fieldLastname.getText());
                query.updateStudent(student);
                showStudents();
                clearFields();
                btnUpdate.setDisable(true);
                btnDelete.setDisable(true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void deleteStudent() {
        try {

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Delete confirmation");
            dialog.setHeaderText(" Are you sure you want to delete a  student?");
            dialog.initModality(Modality.APPLICATION_MODAL);
            Label label = new Label("Name:" + fieldFirstname.getText() + "" + fieldLastname.getText());

            dialog.getDialogPane().setContent(label);
            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);

            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == okButton) {
                com.skpcorp.crud.data.AppQuery query = new com.skpcorp.crud.data.AppQuery();
                com.skpcorp.crud.model.Student student = new com.skpcorp.crud.model.Student(this.student.getId(), fieldFirstname.getText(), fieldMiddlename.getText(), fieldLastname.getText());
                query.deleteStudent(student);
                showStudents();
                clearFields();
                btnUpdate.setDisable(true);
                btnDelete.setDisable(true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void clearFields() {
        fieldFirstname.setText("");
        fieldMiddlename.setText("");
        fieldLastname.setText("");
    }

    @FXML
    private void clicknewbutton() {
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
        clearFields();
        btnNew.setDisable(false);
        btnSave.setDisable(false);
    }

    @FXML
    private void filterData(String searchName) {
        ObservableList<Student> filterData = FXCollections.observableArrayList();
        com.skpcorp.crud.data.AppQuery query = new com.skpcorp.crud.data.AppQuery();
        ObservableList<Student> list = query.getStudentList();
        for (Student student : list) {
            if (student.getFirstname().toLowerCase().contains(searchName.toLowerCase())
                    || student.getMiddlename().toLowerCase().contains(searchName.toLowerCase())
                    || student.getLastname().toLowerCase().contains(searchName.toLowerCase())) {
filterData.add(student);
            }
        }
        tableView.setItems(filterData);
    }

}

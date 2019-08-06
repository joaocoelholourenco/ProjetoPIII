/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifg.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author joaovitor
 */
public class FXMLCadastrarController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    private Button btnPaciente;

    @FXML
    private Button btnMedico;

    @FXML
    void telaMedico(ActionEvent event) {
        MudarTelaController.telaCadastrarMedico();
    }

    @FXML
    void telaPaciente(ActionEvent event) {
        MudarTelaController.telaCadastrarPaciente();
    }
    @FXML
    void telaLogin(ActionEvent event) {
        MudarTelaController.telaLogin();
    }
    
}

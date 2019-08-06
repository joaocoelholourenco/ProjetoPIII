/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifg.controller;

import com.ifg.view.CentroMedicoPIIIvII;
import com.ifg.model.Paciente;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author joaovitor
 */
public class FXMLCadastrarPacienteController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private Button btnCadastrarMedico;

    @FXML
    private Hyperlink btnJaTemConta;

    @FXML
    private Label lbErrosCadastro;

    @FXML
    private Button btnVoltar;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtId;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtTelefone;

    @FXML
    void VoltarCadastrar(ActionEvent event) {
        MudarTelaController.telaCadastrar();
    }

    @FXML
    void telaLogin(ActionEvent event) {
        MudarTelaController.telaLogin();
    }

    @FXML
    void cadastrarPaciente(ActionEvent event) {
        InsertControle ic = new InsertControle();
        ic.ValidaCadastroP(txtId.getText(), txtNome.getText(), txtEmail.getText(), txtTelefone.getText(), txtSenha.getText());

    }
}

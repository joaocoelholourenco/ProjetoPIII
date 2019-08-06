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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author joaovitor
 */
public class FXMLCadastrarMedicoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        System.out.println("Teste");
    }

    @FXML
    private Button btnCadastrarPaciente;

    @FXML
    private Hyperlink btnJaTemConta;

    @FXML
    private Label lbErrosCadastro;
    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtSenha;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtTelefone;

    @FXML
    private TextField txtCRM;

    @FXML
    private Button btnVoltar;

    @FXML
    void VoltarCadastrar(ActionEvent event) {
        MudarTelaController.telaCadastrar();
    }

    @FXML
    void telaLogin(ActionEvent event) {
        MudarTelaController.telaLogin();
    }

    /**
     * Validação dos dados de cadastro.
     */
    @FXML
    void cadastrarMedico(ActionEvent event) {

        InsertControle ic = new InsertControle();
        ic.ValidaCadastroM(txtUsuario.getText(), txtNome.getText(), txtEmail.getText(), txtTelefone.getText(), txtCRM.getText(), txtSenha.getText());

    }

}

package com.ifg.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
//J
public class FXMLRecuperarSenhaController {

    InsertControle ic = new InsertControle();

    @FXML
    private TextField txtEmailRecuperar;

    @FXML
    private TextField txtNovaSenha;

    @FXML
    private TextField txtNovoID;

    @FXML
    private Button btnConfirmarRecuperacao;

    @FXML
    private Button btnVoltarLogin;

    @FXML
    private Button btnAtivarNovoSenha;

    @FXML
    private Button btnAtivarNovoID;

    @FXML
    void ativarNovoID(ActionEvent event) {
        if (txtNovoID.isVisible() == false) {
            txtNovoID.setVisible(true);
            btnAtivarNovoID.setText("-");
        } else {
            btnAtivarNovoID.setText("+");
            txtNovoID.setVisible(false);
        }
    }

    @FXML
    void ativarNovoSenha(ActionEvent event) {
        if (txtNovaSenha.isVisible() == false) {
            txtNovaSenha.setVisible(true);
            btnAtivarNovoSenha.setText("-");
        } else {
            btnAtivarNovoSenha.setText("+");
            txtNovaSenha.setVisible(false);
        }
    }

    @FXML
    void confimarRecuperacao(ActionEvent event) {
        txtNovaSenha.setText(ic.RecuperarSenha(txtEmailRecuperar.getText(), "paciente"));
        txtNovoID.setText(ic.RecuperarId(txtEmailRecuperar.getText(), "paciente"));

        txtNovaSenha.setText(ic.RecuperarSenha(txtEmailRecuperar.getText(), "medico"));
        txtNovoID.setText(ic.RecuperarId(txtEmailRecuperar.getText(), "medico"));
        verificarEmail();
    }

    @FXML
    void voltarLogin(ActionEvent event) {
        setarBranco();
        MudarTelaController.telaLogin();
    }

    private void verificarEmail() {
        if (txtNovaSenha.getText().equals("Email inválido")) {
            txtNovaSenha.setText(ic.RecuperarSenha(txtEmailRecuperar.getText(), "paciente"));
            txtNovoID.setText(ic.RecuperarId(txtEmailRecuperar.getText(), "paciente"));
        }
    }

    private void setarBranco() {
        txtEmailRecuperar.setText("");
        txtNovaSenha.setText("");
        txtNovoID.setText("");
    }
}

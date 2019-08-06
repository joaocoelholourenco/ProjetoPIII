/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifg.controller;

import com.ifg.model.Medico;
import com.ifg.model.Paciente;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author joaovitor
 */
public class FXMLTrocarMedicoController implements Initializable {

    InsertControle ic = new InsertControle();
    Paciente paciente = Paciente.getInstance();
    Medico medico = Medico.getInstance();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private TextField txtPesquisar;

    @FXML
    private Button btnPesquisar;

    @FXML
    private Button btnConfirmarTroca;

    @FXML
    private Button btnCancelarTroca;

    @FXML
    private Label lbNomeMedico;

    @FXML
    void cancelarTroca(ActionEvent event) {
        MudarTelaController.telaPaciente();
    }

    @FXML
    void confirmarTroca(ActionEvent event) {
        try {
            long idDoMedico = Long.parseLong(txtPesquisar.getText());
//        String nome = ic.ConsultaNomeM(id);

            if (ic.trocarMedico(paciente.getId(), idDoMedico) && ic.validarMedicoID(idDoMedico)) {
                JOptionPane.showMessageDialog(null, "Médico trocado com sucesso");
                paciente.setMedico_id((int) idDoMedico);
                MudarTelaController.telaPaciente();
            } else {
                JOptionPane.showMessageDialog(null, "ID não encontrado");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Valor inválido");
        } catch (SQLException e) {

        }
    }

    @FXML
    void pesquisarMedico(ActionEvent event) {
        try {
            long id = Long.parseLong(txtPesquisar.getText());
            String nome = ic.ConsultaNomeM(id);
            if (nome.equals("")) {
                btnConfirmarTroca.setDisable(true);
                lbNomeMedico.setText("Insira um ID Válido!");
                JOptionPane.showMessageDialog(null, "ID não encontrado");
            } else {
                btnConfirmarTroca.setDisable(false);
                lbNomeMedico.setText(nome);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada invalida");
        }
    }
}

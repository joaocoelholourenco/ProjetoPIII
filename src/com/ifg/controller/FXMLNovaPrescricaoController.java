package com.ifg.controller;

import com.ifg.model.Medico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

public class FXMLNovaPrescricaoController {

    InsertControle ic = new InsertControle();
    Medico medico = Medico.getInstance();

    @FXML
    private TextArea txtPrescricao;

    @FXML
    private Label lbNomePaciente;

    @FXML
    private TextField txtPacientePesquisado;

    @FXML
    private Button btnPesquisarPaciente;

    @FXML
    private Button btnConfirmarPrescricao;

    @FXML
    private Button btnCancelarPrescricao;

    @FXML
    void cancelarPrescricao(ActionEvent event) {
        MudarTelaController.telaMedico();
    }

    @FXML
    void confirmarPrescricao(ActionEvent event) {
        try {
            long id = Long.parseLong(txtPacientePesquisado.getText());
            String consulta = ic.VeConsulta(id, medico.getId());

            if (!consulta.equals("Sem consulta")) {

                boolean w = ic.prescId(id, txtPrescricao.getText(), medico.getId());
                if (w == true) {
                    JOptionPane.showMessageDialog(null, "Prescrição realizada");
                } else {
                    JOptionPane.showMessageDialog(null, "Ocorreu algum erro");
                }

            }else{
                JOptionPane.showMessageDialog(null, "Paciente não pode receber a prescrição");
            }

           
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Formato de ID invalido");
        }

    }

    @FXML
    void pesquisarPaciente(ActionEvent event) {
        try {

            long id = Long.parseLong(txtPacientePesquisado.getText());
            String nome = ic.ConsultaNomeP(id, medico.getId());
            if (nome.equals("")) {
                lbNomePaciente.setText("");
            } else {
                lbNomePaciente.setText(nome);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Formato numero invalido");
        }

    }

}

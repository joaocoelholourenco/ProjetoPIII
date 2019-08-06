/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifg.controller;

import com.ifg.model.Conexao;
import com.ifg.model.Paciente;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class FXMLNovaConsultaController implements Initializable {

    Conexao conec = new Conexao();
    InsertControle ic = new InsertControle();
    Paciente paciente = Paciente.getInstance();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    private TextField txtData;

    @FXML
    private Button btnConfirmaNovaConsulta;

    @FXML
    private Button btnCancelarNovaConsulta;

    @FXML
    private Label lbNomeMedico;

    @FXML
    void cancelarNovaConsulta(ActionEvent event) {
        MudarTelaController.telaPaciente();
    }

    @FXML
    void confirmaNovaConsulta(ActionEvent event) throws SQLException {
        String consulta = txtData.getText();

        if (verificaData(consulta)) {
            ic.novaConsulta(consulta, paciente.getId());
        }

    }

    /**
     * verifica se a data e hora sao validas retorna true se for valida e false
     * se nao for
     *
     * @param consulta
     * @return autentificacao
     */
    boolean verificaData(String consulta) throws SQLException {
        boolean autentificacao = false;
        String sql = "select consulta from paciente";
        Connection Cn = (Connection) conec.abrirBDConn();
        PreparedStatement pst = Cn.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            String consultaBD = rs.getString("consulta");
            System.out.println(consultaBD);
            boolean k = consultaRegex(consulta);
            if (!consultaBD.equals(consulta) && k) {
                autentificacao = true;
            } else if (k == false) {
                break;
            } else if (consultaBD.equals(consulta)) {
                JOptionPane.showMessageDialog(null, "Já existe uma consulta nesse horário");
                autentificacao = false;
                break;
            }
        }
        return autentificacao;
    }
    //"^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$,^(0[0-9]|1[0-9]|2[0-3]|[0-9]):[0-5][0-9]$"

    /**
     * verifica se a data e a hora estão no formato válido
     *
     * @param consulta
     * @return
     */
    boolean consultaRegex(String consulta) {
        boolean a = false;

        String[] data_hora = consulta.split(",");
        if (data_hora[0].matches("^\\d{4}-\\d{2}-\\d{2}$") && data_hora[1].matches("^(0[0-9]|1[0-9]|2[0-3]|[0-9]):[0-5][0-9]$")) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Formato inváldo\nYYYY-mm-dd,HH:mm");
        }
        return a;
    }
}

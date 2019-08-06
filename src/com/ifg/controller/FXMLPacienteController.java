/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifg.controller;

import com.ifg.model.Paciente;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author joaovitor
 */
public class FXMLPacienteController implements Initializable {

    ArrayList<String> data = new ArrayList<String>();
    ArrayList<String> nome = new ArrayList<String>();

    Paciente paciente = Paciente.getInstance();
    InsertControle ic = new InsertControle();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    private void telaLogin() {
        MudarTelaController.telaLogin();
    }
    @FXML
    private Hyperlink btnMinhasConsultas;
    @FXML
    private AnchorPane ancCONSULTA;

    @FXML
    private Hyperlink btnMeuMedico;

    @FXML
    private Hyperlink btnMinhasReceitas;

    @FXML
    private Button btnSair;

    @FXML
    private AnchorPane ancMinhasReceitas;

    @FXML
    private TextArea txtPrescricao;

    @FXML
    private Label lbNomeMedicoReceitas;

    @FXML
    private Label lbDataReceitas;

    @FXML
    private AnchorPane ancMeuMedico;

    @FXML
    private Label lbNomeMeuMedico;

    @FXML
    private Button btnTrocarMedico;

    @FXML
    private AnchorPane ancMinhasConsultas;

    @FXML
    private Button btnNovaConsulta;

    @FXML
    private Button btnCancelarConsulta;

    @FXML
    private Label lbNomeMedico;

    @FXML
    private Label lbData;
    @FXML
    private Hyperlink btnConfiguracoesPerfil;

    @FXML
    private AnchorPane ancTelaConfiguracoes;

    @FXML
    private AnchorPane telaConfiguracoes;

    @FXML
    private TextField pacienteNome;

    @FXML
    private TextField pacienteId;

    @FXML
    private TextField pacienteEmail;

    @FXML
    private TextField pacienteNumero;

    @FXML
    private TextField pacienteSenha;

    @FXML
    private Button btnEditarAlteracoes;

    @FXML
    private Button btnExcluirUsuario;

    @FXML
    private Button btnSalvarAlteracoes;

    @FXML
    void configuracoesPerfil(ActionEvent event) {
        setarTextConfig();
        ativarConfigura();
    }

    @FXML
    void editarAlteracoes(ActionEvent event) {
        ativarTextfileds();
    }

    @FXML
    void excluirUsuario(ActionEvent event) {
        try {
            String nome = pacienteNome.getText();
            long id = Long.parseLong(pacienteId.getText());
            String email = pacienteEmail.getText();
            String telefone = pacienteNumero.getText();
            String senha = pacienteSenha.getText();
            boolean k = true;
            k = ic.ExluiOuAlteraPaciente(id, nome, email, telefone, senha, k);
            if (k == true) {
                JOptionPane.showMessageDialog(null, "Exclusão feita com sucesso");
                setarTelaemBranco();
                MudarTelaController.telaLogin();
            } else {
                JOptionPane.showMessageDialog(null, "Exclusão falhou, verifique se passou todos os dados corretamente!");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Formato invalido!");
        }
    }

    @FXML
    void salvarAlteracoes(ActionEvent event) {
        try {
            String nomeP = pacienteNome.getText();
            long id = Long.parseLong(pacienteId.getText());
            String email = pacienteEmail.getText();
            String telefone = pacienteNumero.getText();
            String senha = pacienteSenha.getText();
            boolean k = false;
//            ic.setPaciente(id);

            boolean j = ic.regexConfiguracoes(nomeP, email, telefone);
            if (j) {
                k = ic.ExluiOuAlteraPaciente(id, nomeP, email, telefone, senha, k);
                if (k) {
                    JOptionPane.showMessageDialog(null, "Alteração feita com sucesso");
                } else {
                    JOptionPane.showMessageDialog(null, "Alteração falhou, verifique se passou todos os dados corretamente!");
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Formato invalido!");
        }
    }

    @FXML
    private Label nome_user;

    @FXML
    void sair(ActionEvent event) {
        setarTelaemBranco();
        telaLogin();
    }

    @FXML
    void cancelarConsulta(ActionEvent event) {
        long id = paciente.getId();
        String nomeP = paciente.getNome();
        ic.RemoverConsultaTelaP(id, nomeP);
        if (lbData.getText().matches("Consulta Removida")) {
            JOptionPane.showMessageDialog(null, "Consulta já foi cancelada");
        } else {
            lbData.setText("Consulta Removida");
            JOptionPane.showMessageDialog(null, "Consulta removida com sucesso");
        }
    }

    @FXML
    void novaConsulta(ActionEvent event) {
        MudarTelaController.telaNovaConsulta();
    }

    @FXML
    void trocarMedico(ActionEvent event) {
        MudarTelaController.telaTrocarMedico();
    }

    @FXML
    void meuMedico(ActionEvent event) {
        lbNomeMeuMedico.setVisible(true);
        ativarMeuMedico();

        nome = ic.AchaNomeP(paciente.getMedico_id());

        lbNomeMeuMedico.setText(nome.get(0));
    }

    @FXML
    void minhasConsultas(ActionEvent event) {
        
        lbNomeMedico.setVisible(true);
        lbData.setVisible(true);
        ativarMinhasConsultas();

        data = ic.AchaDataP(paciente.getId());
        nome = ic.AchaNomeP(paciente.getMedico_id());
        if (nome.get(0).equals("Sem médico")) {
            btnCancelarConsulta.setDisable(true);
            btnNovaConsulta.setDisable(true);
            lbNomeMedico.setText("Cadastre-se seu Médico:  ");
            lbData.setText("Meu Médico>>>Casdastrar Médico");
        } else {
            btnCancelarConsulta.setDisable(false);
            btnNovaConsulta.setDisable(false);
            System.out.println(data.get(0));
            lbNomeMedico.setText(nome.get(0));
            lbData.setText(data.get(0));
        }

    }

    @FXML
    void minhasReceitas(ActionEvent event) {
        lbNomeMedicoReceitas.setVisible(true);
        lbDataReceitas.setVisible(true);
        ativarMinhasReceitas();

        data = ic.AchaDataP(paciente.getId());
        nome = ic.AchaNomeP(paciente.getMedico_id());

        lbNomeMedicoReceitas.setText(nome.get(0));
        lbDataReceitas.setText(data.get(0));

        txtPrescricao.setText(ic.pacienteReceita(paciente.getId()));
    }

    private void ativarMeuMedico() {
        ancMinhasConsultas.setDisable(true);
        ancMinhasConsultas.setOpacity(0);
        ancMinhasReceitas.setDisable(true);
        ancMinhasReceitas.setOpacity(0);
        ancTelaConfiguracoes.setDisable(true);
        ancTelaConfiguracoes.setOpacity(0);
        ancMeuMedico.setDisable(false);
        ancMeuMedico.setOpacity(1);
    }

    private void ativarMinhasConsultas() {
        ancMeuMedico.setDisable(true);
        ancMeuMedico.setOpacity(0);
        ancMinhasReceitas.setDisable(true);
        ancMinhasReceitas.setOpacity(0);
        ancTelaConfiguracoes.setDisable(true);
        ancTelaConfiguracoes.setOpacity(0);
        ancMinhasConsultas.setDisable(false);
        ancMinhasConsultas.setOpacity(1);
    }

    private void ativarMinhasReceitas() {

        ancMeuMedico.setDisable(true);
        ancMeuMedico.setOpacity(0);
        ancMinhasConsultas.setDisable(true);
        ancMinhasConsultas.setOpacity(0);
        ancTelaConfiguracoes.setDisable(true);
        ancTelaConfiguracoes.setOpacity(0);
        ancMinhasReceitas.setDisable(false);
        ancMinhasReceitas.setOpacity(1);
    }

    private void ativarTextfileds() {
//        pacienteNome.setEditable(true);
//        pacienteId.setEditable(true);
//        pacienteEmail.setEditable(true);
//        pacienteNumero.setEditable(true);
//        pacienteSenha.setEditable(true);
        pacienteNome.setEditable(true);
        pacienteId.setEditable(false);
        pacienteEmail.setEditable(true);
        pacienteNumero.setEditable(true);
        pacienteSenha.setEditable(false);
    }

    private void ativarConfigura() {
        ancMeuMedico.setDisable(true);
        ancMeuMedico.setOpacity(0);
        ancMinhasConsultas.setDisable(true);
        ancMinhasConsultas.setOpacity(0);
        ancMinhasReceitas.setDisable(true);
        ancMinhasReceitas.setOpacity(0);
        ancTelaConfiguracoes.setDisable(false);
        ancTelaConfiguracoes.setOpacity(1);
    }

    private void setarTextConfig() {
//        pacienteNome.setText(paciente.getNome());
        String a = paciente.getNome();
        pacienteNome.setText(paciente.getNome());
        pacienteId.setText(String.valueOf(paciente.getId()));
        pacienteEmail.setText(paciente.getEmail());
        pacienteNumero.setText(paciente.getTelefone());
        pacienteSenha.setText(paciente.getSenha());
    }

    private void setarTelaemBranco() {
        ancMeuMedico.setDisable(true);
        ancMeuMedico.setOpacity(0);
        ancMinhasConsultas.setDisable(true);
        ancMinhasConsultas.setOpacity(0);
        ancMinhasReceitas.setDisable(true);
        ancMinhasReceitas.setOpacity(0);
        ancTelaConfiguracoes.setDisable(true);
        ancTelaConfiguracoes.setOpacity(0);
    }
}

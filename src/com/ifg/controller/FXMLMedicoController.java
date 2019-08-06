/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifg.controller;

import com.ifg.model.Medico;
import com.ifg.view.CentroMedicoPIIIvII;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author joaovitor
 */
public class FXMLMedicoController implements Initializable {

    Medico medico = Medico.getInstance();
    InsertControle ic = new InsertControle();
    ArrayList<String> data = new ArrayList<String>();
    ArrayList<String> nome = new ArrayList<String>();
    int i = 0;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    @FXML
    private Label nome_user;
    @FXML
    private Hyperlink bntMinhaAgendaMedico;

    @FXML
    private Hyperlink meusPacientes;

    @FXML
    private Hyperlink historicoReceitas;

    @FXML
    private AnchorPane ancTelaMeusPacientes;

    @FXML
    private AnchorPane ancTelaConfiguracoes;

    @FXML
    private AnchorPane ancTelaHistoricoReceitas;

    @FXML
    private AnchorPane grupoItem;

    @FXML
    private AnchorPane telaConfiguracoes;

    @FXML
    private Button btnProximoPaciente;

    @FXML
    private Button btnAnteriorMeusPacientes;

    @FXML
    private Button btnProximoHistorico;

    @FXML
    private Button btnAnteriorHistorico;

    @FXML
    private AnchorPane grupoItem01;

    @FXML
    private Button btnCancelarConsulta01;

    @FXML
    private Button btnPrescricaoConsulta01;

    @FXML
    private Label nomePaciente01;

    @FXML
    private Label data01;

    @FXML
    private AnchorPane grupoItem02;

    @FXML
    private Button btnCancelarConsulta02;

    @FXML
    private Button btnPrescricaoConsulta02;

    @FXML
    private Label nomePaciente02;

    @FXML
    private Label data02;

    @FXML
    private AnchorPane grupoItem0;

    @FXML
    private Button btnCancelarConsulta0;

    @FXML
    private Button btnPrescricaoConsulta0;

    @FXML
    private Label nomePaciente0;

    @FXML
    private Label data0;

    @FXML
    private Button btnPesquisarConsulta;

    @FXML
    private TextField txtPesquisaConsulta;
    @FXML
    private Button btnEditarAlteracoes;

    @FXML
    private Button btnExcluirUsuario;

    @FXML
    private Button btnSalvarAlteracoes;

    @FXML
    private TextField nomeMedico;

    @FXML
    private TextField crmMedico;

    @FXML
    private TextField idMedico;

    @FXML
    private TextField emailMedico;

    @FXML
    private TextField numeroMedico;

    @FXML
    private TextField senhaMedico;

    @FXML
    private Button btnIdPacientes;

    @FXML
    void pesquisarConsulta(ActionEvent event) {
        nomePaciente0.setVisible(false);
        nomePaciente02.setVisible(false);
        nomePaciente01.setVisible(false);
        data0.setVisible(false);
        data01.setVisible(false);
        data02.setVisible(false);
        try {
            long id = Long.parseLong(txtPesquisaConsulta.getText());
            String dataP = ic.ConsultaDataP(id, medico.getId());
            String nomeP = ic.ConsultaNomeP(id, medico.getId());

            if (nomeP.equals("") && dataP.equals("")) {
                JOptionPane.showMessageDialog(null, "Paciente não encontrado");
                nomePaciente01.setVisible(false);
                nomePaciente02.setVisible(false);
                data01.setVisible(false);
                data02.setVisible(false);
                nomePaciente0.setVisible(false);
                data0.setVisible(false);
            } else {
                nomePaciente0.setVisible(true);
                data0.setVisible(true);
                nomePaciente0.setText(nomeP);
                data0.setText(dataP);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada invalida");
        }

    }

    @FXML
    void idPacientes(ActionEvent event) {
        try {
            ic.idP(medico.getId());
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            Logger.getLogger(FXMLMedicoController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void prescricaoConsulta0(ActionEvent event) {
        MudarTelaController.telaNovaPrescricao();

    }

    @FXML
    void prescricaoConsulta01(ActionEvent event) {
        MudarTelaController.telaNovaPrescricao();
    }

    @FXML
    void prescricaoConsulta02(ActionEvent event) {
        MudarTelaController.telaNovaPrescricao();
    }

    @FXML
    void anteriorMeusPacientes(ActionEvent event) {
        nomePaciente0.setVisible(true);
        nomePaciente02.setVisible(true);
        nomePaciente01.setVisible(true);
        data0.setVisible(true);
        data01.setVisible(true);
        data02.setVisible(true);
        ativarMeusPacientes();

        data = ic.AchaData(medico.getId());
        nome = ic.AchaNome(medico.getId());
        int tamanho = nome.size();
        switch (tamanho) {
            case 1:
                nomePaciente0.setText(nome.get(0));
                data0.setText(data.get(0));
                nomePaciente01.setVisible(false);
                nomePaciente02.setVisible(false);
                data01.setVisible(false);
                data02.setVisible(false);
                break;
            case 2:
                nomePaciente0.setText(nome.get(0));
                nomePaciente01.setText(nome.get(1));
                data0.setText(data.get(0));
                data01.setText(data.get(1));
                data02.setVisible(false);
                nomePaciente02.setVisible(false);
                break;
            default:
                nomePaciente0.setText(nome.get(0));
                nomePaciente01.setText(nome.get(1));
                nomePaciente02.setText(nome.get(2));
                data0.setText(data.get(0));
                data01.setText(data.get(1));
                data02.setText(data.get(2));
                break;

        }

    }

    @FXML
    void proximoPaciente(ActionEvent event) {
        ativarMeusPacientes();
        data = ic.AchaData(medico.getId());
        nome = ic.AchaNome(medico.getId());
        i++;
        try {

            int tamanho = nome.size();
            switch (tamanho) {

                case 4:
                    nomePaciente0.setText(nome.get(3));
                    data0.setText(data.get(3));
                    nomePaciente01.setVisible(false);
                    nomePaciente02.setVisible(false);
                    data01.setVisible(false);
                    data02.setVisible(false);
                    break;
                case 5:
                    nomePaciente0.setText(nome.get(3));
                    nomePaciente01.setText(nome.get(4));
                    data0.setText(data.get(3));
                    data01.setText(data.get(4));
                    data02.setVisible(false);
                    nomePaciente02.setVisible(false);
                    break;
                case 6:
                    nomePaciente0.setText(nome.get(3));
                    nomePaciente01.setText(nome.get(4));
                    nomePaciente02.setText(nome.get(5));
                    data0.setText(data.get(3));
                    data01.setText(data.get(4));
                    data02.setText(data.get(5));

                    break;
                default:
//                    JOptionPane.showConfirmDialog(null, "Limite de pacientes visiveis atingido\nProcure por pacientes no campo Pesquisar Paciente");

            }
            if (i > 1) {
                JOptionPane.showMessageDialog(null, "Limite de pacientes visiveis é de duas paginas\nProcure por outros pacientes no campo Pesquisar Paciente");

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Você não tem mais pacientes");

        }

    }

    @FXML
    void historicoReceitas(ActionEvent event) {
        try {
            ic.CriarArqHistorico(medico.getId());

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    void meusPacientes(ActionEvent event) {

        data = ic.AchaData(medico.getId());
        nome = ic.AchaNome(medico.getId());
        i = 0;
        int tamanho = nome.size();
        if (tamanho != 0) {
            ativarMeusPacientes();
            nomePaciente0.setVisible(true);
            nomePaciente02.setVisible(true);
            nomePaciente01.setVisible(true);
            data0.setVisible(true);
            data01.setVisible(true);
            data02.setVisible(true);
            switch (tamanho) {

                case 1:
                    nomePaciente0.setText(nome.get(0));
                    data0.setText(data.get(0));
                    nomePaciente01.setVisible(false);
                    nomePaciente02.setVisible(false);
                    data01.setVisible(false);
                    data02.setVisible(false);
                    break;
                case 2:
                    nomePaciente0.setText(nome.get(0));
                    nomePaciente01.setText(nome.get(1));
                    data0.setText(data.get(0));
                    data01.setText(data.get(1));
                    data02.setVisible(false);
                    nomePaciente02.setVisible(false);
                    break;
                default:
                    nomePaciente0.setText(nome.get(0));
                    nomePaciente01.setText(nome.get(1));
                    nomePaciente02.setText(nome.get(2));
                    data0.setText(data.get(0));
                    data01.setText(data.get(1));
                    data02.setText(data.get(2));
                    break;

            }
            System.out.println(tamanho);
        } else {
            JOptionPane.showMessageDialog(null, "Você nao possui pacientes;");
        }
    }

    @FXML
    void cancelarConsulta0(ActionEvent event) {
        try {
            String str = JOptionPane.showInputDialog(null, "Informe o ID do paciente: ");

            long id = Long.parseLong(str);
            boolean val = ic.RemoverConsulta(id, medico.getId());
            if (val == true) {
                JOptionPane.showMessageDialog(null, "Remoção feita com sucesso! ");
            } else {
                JOptionPane.showMessageDialog(null, "Login não encontrado!\nRemoção ja feita! ");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Apenas numeros");
        }
    }

    @FXML
    void cancelarConsulta01(ActionEvent event) {
//        try {
//            String str = JOptionPane.showInputDialog(null, "Informe o ID do paciente: ");
//
//            long id = Long.parseLong(str);
//            boolean val = ic.RemoverConsulta(id,123123);
//            if (val == true) {
//                JOptionPane.showMessageDialog(null, "Remoção feita com sucesso! ");
//            } else {
//                JOptionPane.showMessageDialog(null, "Login não encontrado!\nRemoção ja feita! ");
//            }
//        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(null, "Apenas numeros");
//        }

    }

    @FXML
    void cancelarConsulta02(ActionEvent event) {
//        try {
//            String str = JOptionPane.showInputDialog(null, "Informe o ID do paciente: ");
//
//            long id = Long.parseLong(str);
//            boolean val = ic.RemoverConsulta(id,23123123);
//            if (val == true) {
//                JOptionPane.showMessageDialog(null, "Remoção feita com sucesso! ");
//            } else {
//                JOptionPane.showMessageDialog(null, "Login não encontrado!\nRemoção ja feita! ");
//            }
//        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(null, "Apenas numeros");
//        }

    }

    @FXML
    void configuracoesPerfil(ActionEvent event) {

        setarTextConfig();
        ativarConfiguracoesPerfil();
    }

    @FXML
    void sair(ActionEvent event) {
        setarTelaEmBranco();
        CentroMedicoPIIIvII.mudarTelas("login");

    }

    @FXML
    void excluirUsuario(ActionEvent event) {
        boolean k = true;

        if (idMedico.getText().matches("[0-9]*") == false || crmMedico.getText().matches("[0-9]*") == false) {
            JOptionPane.showMessageDialog(null, "Usuario ou CRM invalidos", "ERRO", 0);

        } else if (nomeMedico.getText().equals("") || senhaMedico.getText().equals("") || emailMedico.getText().equals("") || numeroMedico.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Um ou mais campos estão vazios", "ERRO", 0);
        } else if (emailMedico.getText().matches(".+@.+\\.[a-z]+") == false) {
            JOptionPane.showMessageDialog(null, "Email invalido", "ERRO", 0);
        } else if (numeroMedico.getText().matches("^\\([1-9]{2}\\)(?:[2-8]|9[1-9])[0-9]{3}\\-[0-9]{4}$") == false) {
            JOptionPane.showMessageDialog(null, "Telefone invalido: formato correto (**)*****-****", "ERRO", 0);
        } else if (nomeMedico.getText().matches("[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]*") == false) {
            JOptionPane.showMessageDialog(null, "Formato de nome invalido", "ERRO", 0);
        } else {
            try {
                String a = nomeMedico.getText();
                long b = Long.parseLong(crmMedico.getText());
                long c = Long.parseLong(idMedico.getText());
                String d = emailMedico.getText();
                String e = numeroMedico.getText();
                String j = senhaMedico.getText();

                k = ic.ExluiOuAlteraMedico(a, b, c, d, e, j, k);

                if (k == true) {
                    JOptionPane.showMessageDialog(null, "Exclusão feita com sucesso");
                    setarTelaEmBranco();
                    CentroMedicoPIIIvII.mudarTelas("login");
                } else {
                    JOptionPane.showMessageDialog(null, "Exclusão falhou, verifique se passou todos os dados corretamente!");
                }
            } catch (NumberFormatException ev) {
                JOptionPane.showMessageDialog(null, "Formato invalido!");
            }
        }
    }

    @FXML
    void editarAlteracoes(ActionEvent event) {
        ativarTextfileds();
    }

//    seta o anchorPane para visualizar a agenda do medico logado
    private void ativarConfiguracoesPerfil() {
        ancTelaMeusPacientes.setDisable(true);
        ancTelaMeusPacientes.setOpacity(0);
        ancTelaConfiguracoes.setDisable(false);
        telaConfiguracoes.setDisable(false);
        ancTelaConfiguracoes.setOpacity(1);
    }

//    seta o anchorPane para visualizar os Pacientes do medico logado
    private void ativarMeusPacientes() {
        ancTelaConfiguracoes.setDisable(true);
        ancTelaConfiguracoes.setOpacity(0);
        ancTelaConfiguracoes.setDisable(false);
        ancTelaMeusPacientes.setDisable(false);
        ancTelaMeusPacientes.setOpacity(1);

    }

//    }
    private void ativarTextfileds() {
        nomeMedico.setEditable(true);
        crmMedico.setEditable(true);
        idMedico.setEditable(true);
        emailMedico.setEditable(true);
        numeroMedico.setEditable(true);
        senhaMedico.setEditable(true);
    }

    @FXML
    void salvarAlteracoes(ActionEvent event) {

        boolean k = false;

        if (idMedico.getText().matches("[0-9]*") == false || crmMedico.getText().matches("[0-9]*") == false) {
            JOptionPane.showMessageDialog(null, "Usuario ou CRM invalidos", "ERRO", 0);

        } else if (nomeMedico.getText().equals("") || senhaMedico.getText().equals("") || emailMedico.getText().equals("") || numeroMedico.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Um ou mais campos estão vazios", "ERRO", 0);
        } else if (emailMedico.getText().matches(".+@.+\\.[a-z]+") == false) {
            JOptionPane.showMessageDialog(null, "Email invalido", "ERRO", 0);
        } else if (numeroMedico.getText().matches("^\\([1-9]{2}\\)(?:[2-8]|9[1-9])[0-9]{3}\\-[0-9]{4}$") == false) {
            JOptionPane.showMessageDialog(null, "Telefone invalido: formato correto (**)*****-****", "ERRO", 0);
        } else if (nomeMedico.getText().matches("[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]*") == false) {
            JOptionPane.showMessageDialog(null, "Formato de nome invalido", "ERRO", 0);
        } else {
            try {
                String a = nomeMedico.getText();
                long b = Long.parseLong(crmMedico.getText());
                long c = Long.parseLong(idMedico.getText());
                String d = emailMedico.getText();
                String e = numeroMedico.getText();
                String j = senhaMedico.getText();

                k = ic.ExluiOuAlteraMedico(a, b, c, d, e, j, k);

                if (k == true) {
                    JOptionPane.showMessageDialog(null, "Alteração feita com sucesso");

                } else {
                    JOptionPane.showMessageDialog(null, "Alteração falhou, verifique se passou todos os dados corretamente!");
                }
            } catch (NumberFormatException ev) {
                JOptionPane.showMessageDialog(null, "Formato invalido!");
            }
        }
    }

    private void setarTextConfig() {
        nomeMedico.setText(medico.getNome());
        crmMedico.setText(String.valueOf(medico.getCrm()));
        idMedico.setText(String.valueOf(medico.getId()));
        emailMedico.setText(medico.getEmail());
        numeroMedico.setText(medico.getTelefone());
        senhaMedico.setText(medico.getSenha());
    }

    private void setarTelaEmBranco() {
        ancTelaMeusPacientes.setDisable(false);
        ancTelaMeusPacientes.setOpacity(0);
        ancTelaConfiguracoes.setDisable(false);
        telaConfiguracoes.setDisable(false);
        ancTelaConfiguracoes.setOpacity(0);
    }

}

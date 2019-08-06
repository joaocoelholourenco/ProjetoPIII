/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifg.controller;

import com.ifg.view.CentroMedicoPIIIvII;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import static javafx.scene.paint.Color.GREEN;
import static javafx.scene.paint.Color.RED;
import javax.swing.JOptionPane;

/**
 *
 * @author 20181080080288
 */
public class FXMLLoginController implements Initializable {

    @FXML
    private TextField txtFieldUser;
    @FXML
    private PasswordField txtFieldSenha;
    @FXML
    public Label lbStatus;
    @FXML
    private Button btnLogin;
    @FXML
    private Hyperlink lbCriarConta;
    @FXML
    private Hyperlink btnEsqueceuConta;

    @FXML
    void esqueceuConta(ActionEvent event) {
        MudarTelaController.telaRecuperarSenha();
    }

    @FXML
    void entrarLogin(ActionEvent event) throws SQLException {
        InsertControle ic = new InsertControle();
        try {
            long id = Long.parseLong(txtFieldUser.getText());

            if (ic.ConsultarLoginM(id, txtFieldSenha.getText()) == true) {
                ic.SetMedico(id);
                CentroMedicoPIIIvII.mudarTelas("telaMedico");

            } else if (ic.ConsultarLoginP(id, txtFieldSenha.getText()) == true) {
               //setPaciente tem q ser aqui
                ic.setPaciente(id); 
                CentroMedicoPIIIvII.mudarTelas("telaPaciente");
            } else {
                JOptionPane.showMessageDialog(null, "Login ou senha errados");

            }
            txtFieldUser.setText("");
            txtFieldSenha.setText("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID invalido: Apenas numeros", "ERRO", 0);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    private String hashgenerator(String senha) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");

            m.update(senha.getBytes(), 0, senha.length());

            byte[] digest = m.digest();

            String hexa = new BigInteger(1, digest).toString(16);
            senha = hexa;
            System.out.println("MD5: " + hexa);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return senha;

    }

    @FXML
    public void modoDeCadastro() throws Exception {
        CentroMedicoPIIIvII.mudarTelas("cadastrar");
    }
}

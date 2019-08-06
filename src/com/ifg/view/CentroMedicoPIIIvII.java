/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifg.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author João Vitor
 */
public class CentroMedicoPIIIvII extends Application {

    private static Stage stage;

    private static Scene sceneLogin;
    private static Scene sceneCadastrar;
    private static Scene sceneCadastrarPaciente;
    private static Scene sceneCadastrarMedico;
    private static Scene scenePaciente;
    private static Scene sceneMedico;
    private static Scene sceneTrocarMedico;
    private static Scene sceneNovaConsulta;
    private static Scene sceneNovaPrescricao;
    private static Scene sceneRecuperarSenha;

    @Override
    public void start(Stage loginStage) throws Exception {
        stage = loginStage;

        loginStage.setTitle("Centro Médico PIII");

        Parent fxmlLogin = FXMLLoader.load(getClass().getResource("../view/FXMLLogin.fxml"));
        sceneLogin = new Scene(fxmlLogin);

        Parent fxmlTrocarMedico = FXMLLoader.load(getClass().getResource("../view/FXMLTrocarMedico.fxml"));
        sceneTrocarMedico = new Scene(fxmlTrocarMedico);

        Parent fxmlCadastrar = FXMLLoader.load(getClass().getResource("../view/FXMLCadastrar.fxml"));
        sceneCadastrar = new Scene(fxmlCadastrar);

        Parent fxmlCadastrarPaciente = FXMLLoader.load(getClass().getResource("../view/FXMLCadastrarPaciente.fxml"));
        sceneCadastrarPaciente = new Scene(fxmlCadastrarPaciente);

        Parent fxmlCadastrarMedico = FXMLLoader.load(getClass().getResource("../view/FXMLCadastrarMedico.fxml"));
        sceneCadastrarMedico = new Scene(fxmlCadastrarMedico);

        Parent fxmlPaciente = FXMLLoader.load(getClass().getResource("../view/FXMLPaciente.fxml"));
        scenePaciente = new Scene(fxmlPaciente);

        Parent fxmlMedico = FXMLLoader.load(getClass().getResource("../view/FXMLMedico.fxml"));
        sceneMedico = new Scene(fxmlMedico);

        Parent fxmlNovaConsulta = FXMLLoader.load(getClass().getResource("../view/FXMLNovaConsulta.fxml"));
        sceneNovaConsulta = new Scene(fxmlNovaConsulta);

        Parent fxmlNovaPrescricao = FXMLLoader.load(getClass().getResource("../view/FXMLNovaPrescricao.fxml"));
        sceneNovaPrescricao = new Scene(fxmlNovaPrescricao);
        
        Parent fxmlRecuperarSenha = FXMLLoader.load(getClass().getResource("../view/FXMLRecuperarSenha.fxml"));
        sceneRecuperarSenha= new Scene(fxmlRecuperarSenha);

        loginStage.setScene(sceneLogin);
        loginStage.show();
    }

    /**
     *
     * Muda as telas
     *
     * @param tela
     */
    public static void mudarTelas(String tela) {
        switch (tela) {
            case "login":
                System.out.println("TELA LOGIN");
                stage.setScene(sceneLogin);
                break;
            case "cadastrar":
                System.out.println("TELA CADASTRAR");
                stage.setScene(sceneCadastrar);
                break;
            case "cadastrarPaciente":
                System.out.println("TELA CADASTRAR PACIENTE");
                stage.setScene(sceneCadastrarPaciente);
                break;

            case "cadastrarMedico":
                stage.setScene(sceneCadastrarMedico);
                System.out.println("TELA CADASTRAR MEDICO");
                break;
            case "telaMedico":
                stage.setScene(sceneMedico);
                System.out.println("TELA MÉDICO");
                break;
            case "telaTrocarMedico":
                stage.setScene(sceneTrocarMedico);
                System.out.println("TELA TROCAR MEDICO");
                break;
            case "telaPaciente":
                stage.setScene(scenePaciente);
                System.out.println("TELA PACIENTE");
                break;
            case "telaNovaConsulta":
                stage.setScene(sceneNovaConsulta);
                System.out.println("TELA NOVA CONSULTA");
                break;
            case "telaNovaPrescricao":
                stage.setScene(sceneNovaPrescricao);
                System.out.println("TELA NOVA PRESCRICAO");
                break;
            case "telaRecuperarSenha":
                stage.setScene(sceneRecuperarSenha);
                System.out.println("TELA RECUPERARSENHA");
                break;
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}

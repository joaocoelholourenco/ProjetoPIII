/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifg.controller;

import com.ifg.view.CentroMedicoPIIIvII;

/**
 *
 * @author joaovitor
 */
public class MudarTelaController {

    public static void telaLogin() {
        CentroMedicoPIIIvII.mudarTelas("login");
    }

    public static void telaCadastrarPaciente() {
        CentroMedicoPIIIvII.mudarTelas("cadastrarPaciente");
    }

    public static void telaCadastrarMedico() {
        CentroMedicoPIIIvII.mudarTelas("cadastrarMedico");
    }

    public static void telaMedico() {
        CentroMedicoPIIIvII.mudarTelas("telaMedico");
    }

    public static void telaPaciente() {
        CentroMedicoPIIIvII.mudarTelas("telaPaciente");
    }

    public static void telaCadastrar() {
        CentroMedicoPIIIvII.mudarTelas("cadastrar");
    }

    public static void telaTrocarMedico() {
        CentroMedicoPIIIvII.mudarTelas("telaTrocarMedico");
    }
    public static void telaNovaPrescricao() {
        CentroMedicoPIIIvII.mudarTelas("telaNovaPrescricao");
    }
    public static void telaNovaConsulta(){
        CentroMedicoPIIIvII.mudarTelas("telaNovaConsulta");
    }
    public static void telaRecuperarSenha(){
        CentroMedicoPIIIvII.mudarTelas("telaRecuperarSenha");
    }
}

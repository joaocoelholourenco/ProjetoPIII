/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifg.model;

/**
 * Classe que serve de modelo para o paciente
 * @author joaovitor
 */
public class Paciente {
 private static Paciente paciente = new Paciente();
/**
     * Construtor padrao
     *
     */
    public Paciente() {
    }
/**
     * Retorna o objeto 
     *
     */
    public synchronized static Paciente getInstance() {
        if (paciente == null) {

           paciente = new Paciente();
        }
        return paciente;

    }
    private int id;
    private String nome;
    private String senha;
    private String email;
    private String telefone;
    private int medico_id;

    public int getMedico_id() {
        return medico_id;
    }

    public void setMedico_id(int medico_id) {
        this.medico_id = medico_id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

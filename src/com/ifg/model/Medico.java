/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifg.model;

/**
 * Classe que serve de modelo para o médico
 *
 * @author joaovitor
 */
public class Medico {

    private long id;
    private String nome;
    private String senha;
    private String email;
    private String telefone;
    private long crm;
    private static Medico medico = new Medico();

   /**
     * Construtor padrao
     *
     */
    public Medico() {
    }

    /**
     * Retorna a instancia do objeto medico
     *
     */
    public synchronized static Medico getInstance() {
        if (medico == null) {

            medico = new Medico();
        }
        return medico;

    }

    public long getCrm() {
        return crm;
    }

    public void setCrm(long crm) {
        this.crm = crm;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}

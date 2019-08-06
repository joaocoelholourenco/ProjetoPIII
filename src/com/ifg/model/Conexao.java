/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifg.model;

/**
 * classe que faz a conexão com o banco de dados
 *
 * @author AM
 */
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

    private String senhaBD = "";
    public Connection con;
    public boolean status = false;

    public Conexao() {
    }

    /**
     * estabelece a conexão
     *
     * @return
     */
    public Connection abrirBDConn() {

        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/projetojava?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");

        } catch (Exception e) {
            System.out.println("Erro ao abrir a conexão" + e);
            return null;
        }
    }

    /**
     * fecha a conexão com o banco de dados
     * 
     */
    public void fecharBDConn() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetojava?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            con.close();
            System.out.println("Conexao finalizada com sucesso");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

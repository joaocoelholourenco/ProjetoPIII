/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifg.model;

/**
 *
 * @author AM
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conexao {

    Connection con;
   

    public Conexao() {
    }

    public Connection abrirBDConn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/projetojava?useTimezone=true&serverTimezone=UCT";
            con = DriverManager.getConnection(url, "root", "");
            System.out.println("Conexao efetuada com sucesso");
            return con;
        } catch (Exception e) {
            System.out.println("Erro ao abrir conexao com banco:");
            e.printStackTrace();
            return null;
        }
    }

    public void fecharBDConn() {
        try {
            con.close();
            System.out.println("Conexao finalizada com sucesso");
        } catch (Exception e) {
            System.out.println("Erro ao fechar conexao com banco" + e.getMessage());
        }
    }
}

package com.ifg.controller;

import com.ifg.model.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class InserirControl {

    Conexao banco = new Conexao();

//    Connection c;
//    protected InserirControl(){
//         this.c = Conexao.abrirBDConn();
//     }
//     public getConnection(){
//     }
    public void InsereDados(String login, String senha, String nome, String CRM, String Email, String Telefone) {

        try {
            Connection Cn = (Connection) banco.abrirBDConn();

            String sSQL = "INSERT INTO login (log ,senha,nome, crm, email, telefone) VALUES (?,?,?,?,?,?);";
            save(sSQL, login, senha, nome, CRM, Email, Telefone, Cn);

            banco.fecharBDConn();
        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Os dados não puderam ser inseridos!!!");
        }
    }

    public boolean consultar(String login, String senha) throws SQLException {
// Manda como parametro para ele duas variaveis para ele procurar no banco de dados!
        try {
            boolean autenticado = false;

            String sql = "select log, senha from login where log='" + login + "'and senha='" + senha + "'";

            Connection Cn = (Connection) banco.abrirBDConn();
            java.sql.Statement stm = Cn.createStatement();

            ResultSet rs;
            rs = stm.executeQuery(sql);

            if (rs.next()) {
                autenticado = true;
            }

            stm.close();
            Cn.close();

            return autenticado;
        } catch (SQLException ex) {
            System.out.println("Erro ao recuperar cliente/ senha.");
            System.out.println(ex);
            throw new RuntimeException();
        }
    }

    private void save(String sSQL, String login, String senha, String nome, String CRM, String Email, String Telefone, Connection Cn) throws SQLException {

        PreparedStatement stmt = Cn.prepareStatement(sSQL);
        stmt.setString(1, login);
        stmt.setString(2, senha);
        stmt.setString(3, nome);
        stmt.setString(4, CRM);
        stmt.setString(5, Email);
        stmt.setString(6, Telefone);
        stmt.executeUpdate();
        stmt.close();

    }

    public Boolean MouP(String log, String senha) throws SQLException {
        boolean autenticacao = false;
        Connection Cn = (Connection) banco.abrirBDConn();

        String SQL = "SELECT log, senha, crm FROM login where log='" + log + "' and senha='" + senha + "'";
        PreparedStatement ps = Cn.prepareStatement(SQL);

        ResultSet rs;
        rs = ps.executeQuery();

        if (rs.next()) {
            String crm = rs.getString("crm");
            if (crm == null) {
                autenticacao = true;
            }
        } else {
            JOptionPane.showMessageDialog(null, rs);

        }

        ps.close();
        banco.fecharBDConn();
        return autenticacao;
    }

    public boolean VerLog(String log, String crm) {
        boolean autenticado = false;
        Connection Cn = (Connection) banco.abrirBDConn();
        try {
            String SQL = "SELECT log, crm FROM login where log='" + log + "' or crm='" + crm + "'";
            PreparedStatement ps = Cn.prepareStatement(SQL);

            ResultSet rs;
            rs = ps.executeQuery();

            if (rs.next()) {

                autenticado = true;

            }

            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        banco.fecharBDConn();
        return autenticado;
    }

}

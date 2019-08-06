/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ifg.controller;

import java.util.Date;
import java.text.SimpleDateFormat;
import com.ifg.model.Conexao;
import com.ifg.model.Medico;
import com.ifg.model.Paciente;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Classe para as operações crud
 *
 */
public class InsertControle {

    Conexao conec = new Conexao();
    Medico medico = Medico.getInstance();
    Paciente paciente = Paciente.getInstance();

    /**
     *
     * Metodo de cadastro do médico.
     *
     * @param id
     * @param crm
     * @param nome
     * @param senha
     * @param email
     * @param telefone
     */
    public void inserirCadastrarMedico(long id, long crm, String nome, String senha, String email, String telefone) {
        try {

            System.out.println("METODO CADASTRAR MEDICO");

            String inserir = "insert into medico(id,crm,nome,email,senha,telefone)values(?,?,?,?,?,?)";
            PreparedStatement pst = conec.abrirBDConn().prepareStatement(inserir);
            pst.setLong(1, id);
            pst.setLong(2, crm);
            pst.setString(3, nome);
            pst.setString(4, email);
            pst.setString(5, senha);
            pst.setString(6, telefone);
            pst.executeUpdate();
            conec.fecharBDConn();
        } catch (SQLException e) {
            System.out.println(e);
        }
        JOptionPane.showMessageDialog(null, "Cadastro Realizado");
        MudarTelaController.telaLogin();
    }

    /**
     *
     * Metodo de cadastro do paciente.
     *
     * @param id
     * @param nome
     * @param senha
     * @param email
     * @param telefone
     */
    public void inserirCadastrarPaciente(long id, String nome, String senha, String email, String telefone) {
        try {

            String inserir = "insert into paciente(id,nome,email,senha,telefone,consulta,presc)values(?,?,?,?,?,?,?)";
            PreparedStatement pst = conec.abrirBDConn().prepareStatement(inserir);
            pst.setLong(1, id);
//            pst.setLong(2, crm);
            pst.setString(2, nome);
            pst.setString(3, email);
            pst.setString(4, senha);
            pst.setString(5, telefone);
            pst.setString(6, "Sem consulta");
            pst.setString(7, "Sem prescrisão");
            pst.executeUpdate();
            conec.fecharBDConn();
        } catch (SQLException e) {
            System.out.println(e);
        }
        JOptionPane.showMessageDialog(null, "Cadastro Realizado");
        MudarTelaController.telaLogin();
    }

    /**
     *
     * metodo pra setar os atributos da classe medico
     *
     * @param id
     * @throws java.sql.SQLException
     */
    public void SetMedico(long id) throws SQLException {

        long crm = -1;//coloquei qualquer valor aqui so pq tinha q inicializar
        String nome = "", senha = "", email = "", telefone = "";
        try {
            String sql = "select * from medico where id=" + id + ";";

            Connection Cn = (Connection) conec.abrirBDConn();
            PreparedStatement pst = Cn.prepareStatement(sql);
            ResultSet rs;
            rs = pst.executeQuery();
            if (rs.next()) {
                nome = rs.getString("nome");
                senha = rs.getString("senha");
                email = rs.getString("email");
                telefone = rs.getString("telefone");
                crm = rs.getInt("crm");

            }
            pst.close();
        } catch (SQLException e) {
            System.out.println(e);

        }
        conec.fecharBDConn();

        medico.setCrm(crm);
        medico.setEmail(email);
        medico.setId(id);
        medico.setNome(nome);
        medico.setSenha(senha);
        medico.setTelefone(telefone);

    }

    /**
     *
     * metodo pra saber se o usuario ou crm já estão em uso.
     *
     * @param crm
     * @param id
     * @param email
     * @return
     * @throws java.sql.SQLException
     *
     */
    public boolean ValidarM(long id, long crm, String email) throws SQLException {

        boolean autenticado = false;
        try {

            String sql = "select p.id,p.email,m.id,m.email,crm from paciente p, medico m where p.id=" + id + " or m.id=" + id + " or p.email='" + email + "' or m.email='" + email + "' or crm=" + crm + ";";

            Connection Cn = (Connection) conec.abrirBDConn();
            PreparedStatement pst = Cn.prepareStatement(sql);
            ResultSet rs;
            rs = pst.executeQuery();
            if (rs.next()) {
                autenticado = true;
            }
            pst.close();
        } catch (SQLException e) {
            System.out.println(e);

        }
        conec.fecharBDConn();

        return autenticado;

    }

    /**
     *
     * metodo pra saber se o usuario já esta em uso.
     *
     * @param id
     * @param email
     * @return
     * @throws java.sql.SQLException
     *
     */
    public boolean ValidarP(long id, String email) throws SQLException {

        boolean autenticado = false;
        try {

            String sql = "select p.id,p.email,m.id,m.email from paciente p, medico m where p.id=" + id + " or m.id=" + id + " or p.email='" + email + "' or m.email='" + email + "';";

            Connection Cn = (Connection) conec.abrirBDConn();
            PreparedStatement pst = Cn.prepareStatement(sql);
            ResultSet rs;
            rs = pst.executeQuery();
            if (rs.next()) {
                autenticado = true;
            }
            pst.close();
        } catch (SQLException e) {
            System.out.println(e);

        }
        conec.fecharBDConn();

        return autenticado;

    }

    /**
     * metodo pra validar o login do Medico
     *
     * @param senha
     * @param id
     * @return
     */
    public boolean ConsultarLoginM(long id, String senha) {

        boolean autenticado = false;
        try {

            String sql = "select id,senha from medico where senha='" + senha + "' and id=" + id + ";";

            Connection Cn = (Connection) conec.abrirBDConn();
            PreparedStatement pst = Cn.prepareStatement(sql);
            ResultSet rs;
            rs = pst.executeQuery();
            if (rs.next()) {
                autenticado = true;
            }
            pst.close();
        } catch (SQLException e) {
            System.out.println(e);

        }
        conec.fecharBDConn();

        return autenticado;
    }

    /**
     * metodo pra validar o login do Paciente
     *
     * @param senha
     * @param id
     * @return
     */
    public boolean ConsultarLoginP(long id, String senha) {

        boolean autenticado = false;
        try {

            String sql = "select id,senha from paciente where senha='" + senha + "' and id=" + id + ";";

            Connection Cn = (Connection) conec.abrirBDConn();
            PreparedStatement pst = Cn.prepareStatement(sql);
            ResultSet rs;
            rs = pst.executeQuery();
            if (rs.next()) {
                autenticado = true;
            }
            pst.close();
        } catch (SQLException e) {
            System.out.println(e);

        }
        conec.fecharBDConn();

        return autenticado;
    }

    /**
     * Valida o cadastro do medico
     *
     * @param id
     * @param nome
     * @param email
     * @param telefone
     * @param crm
     * @param senha
     *
     */
    public void ValidaCadastroM(String id, String nome, String email, String telefone, String crm, String senha) {

        if (id.matches("[0-9]*") == false || crm.matches("[0-9]*") == false) {
            JOptionPane.showMessageDialog(null, "Usuario ou CRM invalidos", "ERRO", 0);
        } else if (nome.equals("") || senha.equals("") || email.equals("") || telefone.equals("")) {
            JOptionPane.showMessageDialog(null, "Um ou mais campos estão vazios", "ERRO", 0);
        } else if (email.matches(".+@.+\\.[a-z]+") == false) {
            JOptionPane.showMessageDialog(null, "Email invalido", "ERRO", 0);
        } else if (telefone.matches("^\\([1-9]{2}\\)(?:[2-8]|9[1-9])[0-9]{3}\\-[0-9]{4}$") == false) {
            JOptionPane.showMessageDialog(null, "Telefone invalido: formato correto (**)*****-****", "ERRO", 0);

        } else {
            long a = Long.parseLong(id);
            long b = Long.parseLong(crm);
            boolean autenticado;
            try {
                autenticado = ValidarM(a, b, email);

                if (autenticado == true) {
                    JOptionPane.showMessageDialog(null, "CRM, Usuario ou Email já cadastrado.", "ERRO", 0);

                } else {
                    inserirCadastrarMedico(a, b, nome, senha, email, telefone);
                }
            } catch (SQLException ex) {
                Logger.getLogger(InsertControle.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    /**
     * Valida o cadastro do paciente
     *
     * @param id
     * @param nome
     * @param email
     * @param telefone
     * @param senha
     *
     */
    public void ValidaCadastroP(String id, String nome, String email, String telefone, String senha) {

        if (id.matches("[0-9]*") == false) {
            JOptionPane.showMessageDialog(null, "Usuario invalido", "ERRO", 0);
        } else if (nome.equals("") || senha.equals("") || email.equals("") || telefone.equals("")) {
            JOptionPane.showMessageDialog(null, "Um ou mais campos estão vazios", "ERRO", 0);
        } else if (email.matches(".+@.+\\.[a-z]+") == false) {
            JOptionPane.showMessageDialog(null, "Email invalido", "ERRO", 0);
        } else if (telefone.matches("^\\([1-9]{2}\\)(?:[2-8]|9[1-9])[0-9]{3}\\-[0-9]{4}$") == false) {
            JOptionPane.showMessageDialog(null, "Telefone invalido: formato correto (**)*****-****", "ERRO", 0);

        } else {
            long a = Long.parseLong(id);

            boolean autenticado;
            try {
                autenticado = ValidarP(a, email);

                if (autenticado == true) {
                    JOptionPane.showMessageDialog(null, "Usuario ou email já cadastrado.", "ERRO", 0);

                } else {
                    inserirCadastrarPaciente(a, nome, senha, email, telefone);
                }
            } catch (SQLException ex) {
                Logger.getLogger(InsertControle.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    /**
     * Faz consulta no bd pelo paciente do medico em questao para setar as
     * labels.
     *
     * @param id
     * @return
     */
    public ArrayList<String> AchaNome(long id) {
        Conexao conec = new Conexao();
        ArrayList<String> nome = new ArrayList();
        String str = "";
        try {

            String sql = "select nome from paciente where medico_id=" + id + ";";
            int i = 0;
            Connection Cn = (Connection) conec.abrirBDConn();
            PreparedStatement pst = Cn.prepareStatement(sql);
            ResultSet rs;
            rs = pst.executeQuery();
            while (rs.next()) {
                nome.add(i, rs.getString("nome"));
                i++;
            }
            pst.close();
        } catch (SQLException e) {
            System.out.println(e);

        }
        conec.fecharBDConn();

        return nome;
    }

    /**
     * Faz consulta no bd do paciente para setar as labels.
     *
     * @param medico_id
     * @return
     */
    public ArrayList<String> AchaNomeP(long medico_id) {
        Conexao conec = new Conexao();
        ArrayList<String> nome = new ArrayList();
        String str = "";
        try {
            if (medico_id != 0) {
                String sql = "select nome from medico where id = " + medico_id + ";";
                int i = 0;
                Connection Cn = (Connection) conec.abrirBDConn();
                PreparedStatement pst = Cn.prepareStatement(sql);
                ResultSet rs;
                rs = pst.executeQuery();
                while (rs.next()) {
                    nome.add(i, rs.getString("nome"));
                    i++;
                }
                pst.close();
            } else {
                nome.add(0, "Sem médico");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        conec.fecharBDConn();

        return nome;
    }

    /**
     * Faz consulta no bd pelo paciente do medico em questao para setar as
     * labels.
     *
     * @param id
     * @return
     */
    public ArrayList<String> AchaData(long id) {
        Conexao conec = new Conexao();
        ArrayList<String> data = new ArrayList();
        String str = "";
        try {

            String sql = "select consulta from paciente where medico_id=" + id + ";";

            Connection Cn = (Connection) conec.abrirBDConn();
            PreparedStatement pst = Cn.prepareStatement(sql);
            ResultSet rs;
            int i = 0;
            rs = pst.executeQuery();
            while (rs.next()) {

                data.add(i, rs.getString("consulta"));
                i++;
            }
            pst.close();
        } catch (SQLException e) {
            System.out.println(e);

        }
        conec.fecharBDConn();

        return data;
    }

    /**
     * Faz consulta no bd do paciente para setar as labels.
     *
     * @param id
     * @return
     */
    public ArrayList<String> AchaDataP(long id) {
        Conexao conec = new Conexao();
        ArrayList<String> data = new ArrayList();
        String str = "";
        try {

            String sql = "select consulta from paciente where Id=" + id + ";";

            Connection Cn = (Connection) conec.abrirBDConn();
            PreparedStatement pst = Cn.prepareStatement(sql);
            ResultSet rs;
            int i = 0;
            rs = pst.executeQuery();
            while (rs.next()) {
                data.add(i, rs.getString("consulta"));
                i++;
            }
            pst.close();
        } catch (SQLException e) {
            System.out.println(e);

        }
        conec.fecharBDConn();

        return data;
    }

    /**
     * Mostra um paciente em especifico
     *
     * @param id
     * @param id_medico
     * @return
     */
    public String ConsultaNomeP(long id, long id_medico) {

        Conexao conec = new Conexao();

        String nome = "";
        try {

            String sql = "select nome from paciente where medico_id=" + id_medico + " and id=" + id + ";";

            Connection Cn = (Connection) conec.abrirBDConn();
            PreparedStatement pst = Cn.prepareStatement(sql);
            ResultSet rs;
            rs = pst.executeQuery();
            while (rs.next()) {
                nome = rs.getString("nome");
            }
            pst.close();
        } catch (SQLException e) {
            System.out.println(e);

        }
        conec.fecharBDConn();

        return nome;

    }

    /**
     * Mostra a data de um paciente especifico
     *
     * @param id
     * @param id_medico
     * @return
     */
    public String ConsultaDataP(long id, long id_medico) {
        Conexao conec = new Conexao();

        String data = "";
        try {

            String sql = "select consulta from paciente where medico_id=" + id_medico + " and id=" + id + ";";

            Connection Cn = (Connection) conec.abrirBDConn();
            PreparedStatement pst = Cn.prepareStatement(sql);
            ResultSet rs;
            rs = pst.executeQuery();
            while (rs.next()) {
                data = rs.getString("consulta");
            }
            pst.close();
        } catch (SQLException e) {
            System.out.println(e);

        }
        conec.fecharBDConn();

        return data;

    }

    /**
     * Mostra um médico em especifico
     *
     * @param id
     * @return
     */
    public String ConsultaNomeM(long id) {
        Conexao conec = new Conexao();

        String nome = "";
        try {

            String sql = "select nome from medico where id=" + id + ";";

            Connection Cn = (Connection) conec.abrirBDConn();
            PreparedStatement pst = Cn.prepareStatement(sql);
            ResultSet rs;
            rs = pst.executeQuery();
            while (rs.next()) {
                nome = rs.getString("nome");
            }
            pst.close();
        } catch (SQLException e) {
            System.out.println(e);

        }
        conec.fecharBDConn();

        return nome;

    }

    /**
     * Preenche os atributos da classe paciente
     *
     * @param id
     *
     */
    public void setPaciente(long id) {

        //coloquei qualquer valor aqui so pq tinha q inicializar
        String nome = "", senha = "", email = "", telefone = "";
        long medico_id = 0;
        try {
            String sql = "select * from paciente where id=" + id + ";";

            Connection Cn = (Connection) conec.abrirBDConn();
            PreparedStatement pst = Cn.prepareStatement(sql);
            ResultSet rs;
            rs = pst.executeQuery();
            if (rs.next()) {
                nome = rs.getString("nome");
                senha = rs.getString("senha");
                email = rs.getString("email");
                telefone = rs.getString("telefone");
                medico_id = rs.getLong("medico_id");
            }
            pst.close();
        } catch (SQLException e) {
            System.out.println(e);

        }
        conec.fecharBDConn();

        paciente.setEmail(email);
        paciente.setId((int) id);
        paciente.setNome(nome);
        paciente.setSenha(senha);
        paciente.setTelefone(telefone);
        paciente.setMedico_id((int) medico_id);

        System.out.println(id + " " + nome + " " + senha + " " + email + " " + telefone);
    }

    /**
     * Remove a Consulta
     *
     * @param id
     * @param idmedico
     * @return
     *
     */
    public boolean RemoverConsulta(long id, long idmedico) {
        boolean val = false;

        try {
            String inserir = "update paciente set consulta ='Sem consulta' where id=(?) and medico_id=" + idmedico + ";";
            PreparedStatement pst = conec.abrirBDConn().prepareStatement(inserir);
            pst.setLong(1, id);
            int a = pst.executeUpdate();
            if (a != 0) {
                val = true;
            }
            conec.fecharBDConn();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return val;
    }

    /**
     * adiciona uma nova consulta
     *
     *
     * @param consulta
     * @param id
     */
    public void novaConsulta(String consulta, long id) {
        try {
//            String sql = "insert into paciente(?) values ('" + consulta + "');";
            String sql2 = "update paciente set consulta ='" + consulta + "' where id=(?);";
            PreparedStatement pst = conec.abrirBDConn().prepareStatement(sql2);
            pst.setLong(1, id);
            pst.executeUpdate();
            conec.fecharBDConn();
            JOptionPane.showMessageDialog(null, "Consulta cadastrada");
            MudarTelaController.telaPaciente();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /**
     * retorna a prescrição do paciente
     *
     *
     * @param id
     * @return
     */
    public String pacienteReceita(long id) {
        String presc = "";
        try {

            String selectSql = "select presc from paciente where id =" + id;
            PreparedStatement pst = conec.abrirBDConn().prepareStatement(selectSql);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                presc = rs.getString("presc");
            }
            pst.executeUpdate();
            conec.fecharBDConn();
        } catch (SQLException e) {

        }
        return presc;
    }

    /**
     *
     * retorna false se não encontrar o id do médico e true caso contrário
     *
     * @param idP
     * @param medico_id
     * @return
     */
    public boolean trocarMedico(long idP, long medico_id) {
        boolean val = false;

        try {
            String inserir = "update paciente set medico_id ='" + medico_id + "' where id=(?);";
            PreparedStatement pst = conec.abrirBDConn().prepareStatement(inserir);
            pst.setLong(1, idP);
            int a = pst.executeUpdate();
            if (a != 0) {
                val = true;
            }
            conec.fecharBDConn();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return val;
    }

    /**
     * metodo para verificar se o id do medico está no BD pra trocar o médico
     *
     *
     * @param id
     * @param presc
     * @param medicoID
     * @return
     */
    boolean validarMedicoID(long MedicoID) throws SQLException {
        String selectSql = "select id from medico where id=" + MedicoID;
        PreparedStatement pst = conec.abrirBDConn().prepareStatement(selectSql);
        ResultSet rs = pst.executeQuery();
        return rs.next();
    }

    /**
     * retorna false se não encontrar o paciente e true caso contrário
     *
     *
     * @param id
     * @param presc
     * @param medicoID
     * @return
     */
    public boolean prescId(long id, String presc, long medicoID) {
        boolean val = false;

        try {
            String inserir = "update paciente set presc ='" + presc + "' where id=(?) and medico_id=" + medicoID + ";";
            PreparedStatement pst = conec.abrirBDConn().prepareStatement(inserir);
            pst.setLong(1, id);
            int a = pst.executeUpdate();
            if (a != 0) {
                val = true;
            }
            conec.fecharBDConn();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return val;
    }

    /**
     *
     * metodo para excluir ou alterar os valores no bd da tabela médico retorna
     * true se conseguir alterar e false se não conseguir alterar
     *
     *
     */
    boolean ExluiOuAlteraMedico(String a, long b, long c, String d, String e, String j, boolean k) {

        if (k == true) {
            k = false;
            try {
                NullMedico(c, j);
                String inserir = "delete from medico where Id =" + c + " and senha='" + j + "';";
                PreparedStatement pst = conec.abrirBDConn().prepareStatement(inserir);

                int z = pst.executeUpdate();
                if (z != 0) {
                    k = true;
                }
                conec.fecharBDConn();
                return k;
            } catch (SQLException ev) {
                JOptionPane.showMessageDialog(null, ev);
            }
        } else {
            try {
                String inserir = "update medico set nome='" + a + "' , crm=" + b + " , email='" + d + "' , telefone='" + e + "' where Id =" + c + " and senha='" + j + "';";
                PreparedStatement pst = conec.abrirBDConn().prepareStatement(inserir);

                int z = pst.executeUpdate();
                if (z != 0) {
                    k = true;
                }
                conec.fecharBDConn();

            } catch (SQLException ev) {
                JOptionPane.showMessageDialog(null, ev);
            }
            return k;
        }
        return k;
    }

    /**
     *
     * metodo para excluir ou alterar os valores no bd da tabela paciente
     * retorna true se conseguir alterar e false se não conseguir alterar
     *
     * @param id
     * @param presc
     * @param medicoID
     * @return
     */
    boolean ExluiOuAlteraPaciente(long id, String nome, String email, String numero, String senha, boolean k) {

        if (k == true) {//para excluir
            k = false;
            try {
//                NullMedico(c, j);
                String inserir = "delete from paciente where Id =" + id + " and senha='" + senha + "';";
                PreparedStatement pst = conec.abrirBDConn().prepareStatement(inserir);

                int z = pst.executeUpdate();
                if (z != 0) {
                    k = true;
                }
                conec.fecharBDConn();
                return k;
            } catch (SQLException ev) {
                JOptionPane.showMessageDialog(null, ev);
            }
        } else {//para alterar
            try {
                String updateSql = "UPDATE paciente SET nome='" + nome + "', email='" + email + "', telefone='" + numero + "', senha='" + senha + "' where id='" + id + "';";
                PreparedStatement pst = conec.abrirBDConn().prepareStatement(updateSql);

                int z = pst.executeUpdate();
                if (z != 0) {
                    k = true;
                }
                conec.fecharBDConn();

            } catch (SQLException ev) {
                JOptionPane.showMessageDialog(null, ev);
            }
            return k;
        }
        return k;
    }

    /**
     * Verifica se o que o paciente digitou esta errado
     *
     * @param nome
     * @param email
     * @param telefone
     * @return
     */
    boolean regexConfiguracoes(String nome, String email, String telefone) {
        boolean autentificado = false;
        if (nome.equals("") || email.equals("") || telefone.equals("")) {
            JOptionPane.showMessageDialog(null, "Um ou mais campos estão vazios", "ERRO", 0);
        } else if (email.matches(".+@.+\\.[a-z]+") == false) {
            JOptionPane.showMessageDialog(null, "Email invalido", "ERRO", 0);
        } else if (telefone.matches("^\\([1-9]{2}\\)(?:[2-8]|9[1-9])[0-9]{3}\\-[0-9]{4}$") == false) {
            JOptionPane.showMessageDialog(null, "Telefone invalido: formato correto (**)*****-****", "ERRO", 0);
        } else {
            autentificado = true;
        }
        return autentificado;
    }

    /**
     * seta como nulo o id do medico da tabela paciente
     *
     * @param medico_id
     */
    private void NullMedico(long medico_id, String senhaMedico) {
        try {
            String inserir = "update paciente, medico set medico_id =" + null + " where medico_id=" + medico_id + " and medico.senha='" + senhaMedico + "';";
            PreparedStatement pst = conec.abrirBDConn().prepareStatement(inserir);

            pst.executeUpdate();
            conec.fecharBDConn();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * Cria um arquivo onde é colocado informacoes sobre as receitas medicas
     *
     * @param id
     *
     *
     */
    void CriarArqHistorico(long id) throws IOException {
        boolean val = false;
        String caminhoArquivo = "C:/Users/Savio/Desktop/";
        String idMedico = (" " + String.valueOf(medico.getId()));
        String nomeArquivo = "PRESCRIÇÃO";
        String caminhoCompleto = caminhoArquivo + nomeArquivo + idMedico;
        ArrayList<String> info = new ArrayList<String>();
        try {

            String sql = "select id, nome, email, presc  from paciente where medico_id=" + id + ";";

            Connection Cn = (Connection) conec.abrirBDConn();
            PreparedStatement pst = Cn.prepareStatement(sql);
            ResultSet rs;
            rs = pst.executeQuery();
            int i = 0;
            while (rs.next()) {
                info.add(i, "Paciente: " + rs.getString("nome") + " ID: " + rs.getInt("id") + " Email: " + rs.getString("email") + "\r\n Prescrição médica:\r\n " + rs.getString("presc") + "\r\n--------------------");
                i++;
                val = true;
            }
            pst.close();
            File Presc = new File(caminhoArquivo + nomeArquivo + idMedico + ".txt");
            FileWriter writer = new FileWriter(Presc, true);

            Date data = new Date();
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
            for (int j = 0; j < info.size(); j++) {
                if (j == 0) {
                    writer.write("Centro Medico :" + formatador.format(data) + "\r\n");
                }
                writer.write(info.get(j) + "\r\n");
            }
            writer.close();

            if (val = true) {
                JOptionPane.showMessageDialog(null, "Arquivo criado em: " + caminhoArquivo + nomeArquivo + idMedico + ".txt");
            }
        } catch (SQLException e) {
            System.out.println(e);

        } catch (IOException ev) {

            System.out.println(ev);
        }

        conec.fecharBDConn();

    }

     /**
      * Metodo pra escrever o id dos pacientes em um arq
     * @param id
     *
     *
     */
    void idP(long id) throws SQLException, IOException {

        String caminhoArquivo = "C:/Users/Savio/Desktop/";
        String idMedico = (" " + String.valueOf(medico.getId()));
        String nomeArquivo = "ID PACIENTES";
        String caminhoCompleto = caminhoArquivo + nomeArquivo + idMedico;

        ArrayList<String> info = new ArrayList<String>();
        try {

            String sql = "select id, nome, email, telefone  from paciente where medico_id=" + id + ";";

            Connection Cn = (Connection) conec.abrirBDConn();
            PreparedStatement pst = Cn.prepareStatement(sql);
            ResultSet rs;
            rs = pst.executeQuery();
            int i = 0;
            while (rs.next()) {
                info.add(i, "Paciente: " + rs.getString("nome") + " ID: " + rs.getInt("id") + " Email: " + rs.getString("email") + " Telefone: " + rs.getString("telefone") + "\r\n--------------------\r\n");
                i++;
            }
            pst.close();
            File Presc = new File(caminhoCompleto + ".txt");
            FileWriter writer = new FileWriter(Presc, true);

            Date data = new Date();
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
            for (int j = 0; j < info.size(); j++) {
                if (j == 0) {
                    writer.write("Seus pacientes até a seguinte data " + formatador.format(data) + " são:\r\n");
                }
                writer.write(info.get(j) + "\r\n");
            }
            writer.close();
            if (info.size() > 0) {
                JOptionPane.showMessageDialog(null, "Seu arquivo foi escrito com sucesso em: " + caminhoCompleto);
            } else {
                JOptionPane.showMessageDialog(null, "Você não possui pacientes!");

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

 /**
      * Recupera o id
     *
     *
     * @param email
     * @param tipo
     * @return 
     */
    public String RecuperarId(String email, String tipo) {
        String senha = "Email inválido";
        try {
            String sql = "select id from " + tipo + " where email='" + email + "';";
            Connection Cn = (Connection) conec.abrirBDConn();
            PreparedStatement pst = Cn.prepareStatement(sql);
            ResultSet rs;

            rs = pst.executeQuery();

            if (rs.next()) {
                senha = rs.getString("Id");

            }
            conec.fecharBDConn();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return senha;
    }

    /**
     * Recupera a senha
     *
     * @param email
     * @param tipo
     * @return
     *
     */
    public String RecuperarSenha(String email, String tipo) {
        String senha = "Email inválido";
        try {
            String sql = "select senha from " + tipo + " where email='" + email + "';";
            Connection Cn = (Connection) conec.abrirBDConn();
            PreparedStatement pst = Cn.prepareStatement(sql);
            ResultSet rs;

            rs = pst.executeQuery();

            if (rs.next()) {
                senha = rs.getString("senha");
                System.out.println(senha);

            }
            conec.fecharBDConn();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return senha;
    }

    /**
     * Ve o que esta escrito no campo consulta do BD
     *
     * @param id
     * @return
     *
     */
    public String VeConsulta(long id, long medicoid) {
        Conexao conec = new Conexao();

        String consulta = "";
        try {

            String sql = "select consulta from paciente where medico_id=" + medicoid + " and id=" + id + ";";

            Connection Cn = (Connection) conec.abrirBDConn();
            PreparedStatement pst = Cn.prepareStatement(sql);
            ResultSet rs;
            rs = pst.executeQuery();
            while (rs.next()) {
                consulta = rs.getString("consulta");
            }
            pst.close();
        } catch (SQLException e) {
            System.out.println(e);

        }
        conec.fecharBDConn();

        return consulta;
    }

    /**
     * Remove a Consulta
     *
     * @param id
     * @param nome
     * @return
     *
     */
    public boolean RemoverConsultaTelaP(long id, String nome) {
        boolean val = false;

        try {
            String inserir = "update paciente set consulta ='Sem consulta' where id=(?) and nome='" + nome + "';";
            PreparedStatement pst = conec.abrirBDConn().prepareStatement(inserir);
            pst.setLong(1, id);
            int a = pst.executeUpdate();
            if (a != 0) {
                val = true;
            }
            conec.fecharBDConn();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return val;
    }

}

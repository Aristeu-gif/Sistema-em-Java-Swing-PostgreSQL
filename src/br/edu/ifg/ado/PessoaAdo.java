/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifg.ado;

import br.edu.ifg.model.Pessoa;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import sun.tools.tree.ThisExpression;

/**
 *
 * @author Aristeu
 */
public class PessoaAdo {

    SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");

    public static List<Pessoa> listaPessoas() {
        String sql = "select * from pessoa";
        ArrayList<Pessoa> lista = new ArrayList<>();

        try {
            PreparedStatement ps = ConectaAoBD.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Pessoa pessoa = new Pessoa(rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5));
                pessoa.setId(rs.getInt(1));

                lista.add(pessoa);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return lista;
    }

    public static List<Pessoa> listaPessoasPorNome(String nome) {
        String sql = "select * from pessoa where pessoa.nome like  ?";
        ArrayList<Pessoa> lista = new ArrayList<>();

        try {
            PreparedStatement ps = ConectaAoBD.getConexao().prepareStatement(sql);
            ps.setString(1, "%" + nome + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Pessoa pessoa = new Pessoa(rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5));
                pessoa.setId(rs.getInt(1));
                lista.add(pessoa);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return lista;
    }

    public static boolean inserePessoa(Pessoa pessoa) {
        String sql = "INSERT INTO pessoa(cpf,nome, sexo,datanascimento) VALUES (?,?,?,?)";
        try {
            PreparedStatement ps = ConectaAoBD.getConexao().prepareStatement(sql);
            ps.setString(1, pessoa.getCpf());
            ps.setString(2, pessoa.getNome());
            ps.setString(3, pessoa.getSexo());
            ps.setDate(4, pessoa.getDataNascimento());
            ps.execute();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public static boolean removePessoa(int id) {
        String sql = "DELETE FROM pessoa WHERE id=? ";
        try {
            PreparedStatement ps = ConectaAoBD.getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {

            return false;
        }

    }

    public static boolean alteraPessoa(Pessoa pessoa) {
        String sql = "UPDATE pessoa SET nome=?, sexo=?, datanascimento=? WHERE cpf=?";
        try {
            PreparedStatement ps = ConectaAoBD.getConexao().prepareStatement(sql);
            ps.setString(4, pessoa.getCpf());
            ps.setString(1, pessoa.getNome());
            ps.setString(2, pessoa.getSexo());
            ps.setDate(3, pessoa.getDataNascimento());

            ps.execute();

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static Pessoa buscaPessoaPorId(int idPessoa) {
        String sql = "select * from pessoa where id = ?";
        try {
            PreparedStatement ps = ConectaAoBD.getConexao().prepareStatement(sql);
            ps.setInt(1, idPessoa);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Pessoa pessoa = new Pessoa(rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5));
                pessoa.setId(rs.getInt(1));
                return pessoa;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean verificaSePessoaDeuMaisDeCincoLances(int idPessoa, int idLeilao) {
        String sql = "select count(*)  from lance join pessoa on pessoa.id = lance.idpessoa join leilao on leilao.id = lance.idleilao "
                + "where pessoa.id = ? and leilao.id=?";
        int quantidade = 0;
        boolean maisDeCinco = false;
        try {
            PreparedStatement ps = ConectaAoBD.getConexao().prepareStatement(sql);
            ps.setInt(1, idPessoa);
            ps.setInt(2, idLeilao);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                quantidade = rs.getInt(1);
            }
            if (quantidade > 5) {
                maisDeCinco = true;
            }
            if (quantidade < 5) {
                maisDeCinco = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maisDeCinco;
    }

    public static int buscaIdDaPessoaDoUltimoLance(int idUltimoLance, int idLeilao) {
        String sql = " select (pessoa.id) from pessoa join lance on lance.idpessoa=pessoa.id join leilao on leilao.id = lance.idLeilao where lance.id=? and leilao.id=?";
        int idPessoa = 0;
        try {
            PreparedStatement ps = ConectaAoBD.getConexao().prepareStatement(sql);
            ps.setInt(1, idUltimoLance);
            ps.setInt(2, idLeilao);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                idPessoa = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idPessoa;
    }

    public static Boolean buscaPessoaPorCpf(String cpf) {
        String sql = "select * from pessoa where cpf = ?";
        try {
            PreparedStatement ps = ConectaAoBD.getConexao().prepareStatement(sql);
            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean verificaSePessoaParticipouDeLances(int id) {
        int quantidade = 0;
        boolean participou = false;
        String sql = "select count(pessoa.id) from pessoa join lances on lance.idPessoa = pessoa.id where pessoa.id = ?;";
        try {
            PreparedStatement ps = ConectaAoBD.getConexao().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                quantidade = rs.getInt("count");
            }
            if (quantidade > 0) {
                participou = true;
            } else {
                participou = false;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return participou;
    }

    public static void main(String[] args) throws Exception {
        String dataRecebida = "01/01/2000";

        //inserePessoa(new Pessoa("1234343","teste com data","M",formataData(dataRecebida)));
//    	
        //System.out.println(verificaSePessoaDeuMaisDeCincoLances(2));
    }

}

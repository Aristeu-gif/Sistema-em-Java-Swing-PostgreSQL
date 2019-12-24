/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifg.ado;

import br.edu.ifg.model.Lance;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aristeu
 */
public class LancesAdo {

    public static List<Lance> listaLances(int idLeilao) {
        String sql = "select * from lance"
                + " join leilao"
                + " on leilao.id = lance.idLeilao"
                + " where leilao.id = ?";
        ArrayList<Lance> lista = new ArrayList<>();
        try {
            PreparedStatement ps = ConectaAoBD.getConexao().prepareStatement(sql);
            ps.setInt(1, idLeilao);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                lista.add(new Lance(rs.getDouble(4), rs.getInt(3), rs.getInt(2)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    public static int buscaOIdDoUltimoLance() {
        int idUltimoLance = 0;
        boolean participou = false;
        String sql = "select max(lance.id) from lance;";
        try {
            PreparedStatement ps = ConectaAoBD.getConexao().prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                idUltimoLance = rs.getInt("MAX");
            }
            

        } catch (Exception e) {
            e.printStackTrace();
        }
        return idUltimoLance;
    }

    public static boolean insereLance(Lance lance) {
        String sql = "INSERT INTO lance(idleilao,idpessoa, valordolance) VALUES (?,?,?)";
        try {
            PreparedStatement ps = ConectaAoBD.getConexao().prepareStatement(sql);
            ps.setInt(1, lance.getIdLeilao());
            ps.setInt(2, lance.getIdPessoa());
            ps.setDouble(3, lance.getValorDoLance());
            ps.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    
    public static int buscaQuantidadeDeLancesDoLeilao(int idLeilao) {
        int idUltimoLance = 0;
        boolean participou = false;
        String sql = "select count(lance.id) from lance join leilao on leilao.id = lance.idleilao where lance.idleilao =?";
        try {
            PreparedStatement ps = ConectaAoBD.getConexao().prepareStatement(sql);
            ps.setInt(1, idLeilao);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                idUltimoLance = rs.getInt("count");
            }
            

        } catch (Exception e) {
            e.printStackTrace();
        }
        return idUltimoLance;
    }
    
    public static String buscaLanceFinal(int idLeilao) {
        String lanceFinal = "";
        boolean participou = false;
        String sql = "select max(lance.valordolance) from lance join leilao on leilao.id = lance.idleilao where lance.idleilao =?";
        try {
            PreparedStatement ps = ConectaAoBD.getConexao().prepareStatement(sql);
            ps.setInt(1, idLeilao);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lanceFinal = rs.getString("max");
            }
            

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lanceFinal;
    }
    
    public static String buscaMenorLance(int idLeilao) {
        String lanceFinal = "";
        boolean participou = false;
        String sql = "select min(lance.valordolance) from lance join leilao on leilao.id = lance.idleilao where lance.idleilao =?";
        try {
            PreparedStatement ps = ConectaAoBD.getConexao().prepareStatement(sql);
            ps.setInt(1, idLeilao);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lanceFinal = rs.getString("min");
            }
            

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lanceFinal;
    }
    
    
    public static void main (String []args){
//         insereLance(new Lance(100,8,2));
         System.out.println(buscaQuantidadeDeLancesDoLeilao(2));
     }
}

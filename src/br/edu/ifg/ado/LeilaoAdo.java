package br.edu.ifg.ado;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import br.edu.ifg.model.LeilaoModel;
import br.edu.ifg.model.Pessoa;
import br.edu.ifg.model.Situacao;
import java.util.ArrayList;

public class LeilaoAdo {

    public static List<LeilaoModel> listaLeiloes() {
        String sql = "select * from leilao";
        ArrayList<LeilaoModel> lista = new ArrayList<>();
        try {
            PreparedStatement ps = ConectaAoBD.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Situacao situacao;
            while (rs.next()) {
                situacao = Situacao.valueOf(rs.getString(4));

                lista.add(new LeilaoModel(rs.getInt(1), rs.getString(2), rs.getDouble(3), situacao));
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return lista;
    }
    public static List<LeilaoModel> listaLeilaoPorSituacao(String situacaoBusca) {
        String sql = "select * from leilao where leilao.situacao like  ?";
        ArrayList<LeilaoModel> lista = new ArrayList<>();
        Situacao situacao;
        try {
            PreparedStatement ps = ConectaAoBD.getConexao().prepareStatement(sql);
            ps.setString(1,  "%" + situacaoBusca + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                 situacao = Situacao.valueOf(rs.getString(4));

                lista.add(new LeilaoModel(rs.getInt(1), rs.getString(2), rs.getDouble(3), situacao));
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return lista;
    }


    public static boolean insereLeilao(LeilaoModel leilaoModel) {
        String sql = "INSERT INTO leilao(item, precoInicial, situacao) VALUES (?,?,?)";
        try {
            PreparedStatement ps = ConectaAoBD.getConexao().prepareStatement(sql);
            ps.setString(1, leilaoModel.getItem());
            ps.setDouble(2, leilaoModel.getPrecoInicial());
            ps.setString(3, leilaoModel.getSituacao().toString());
            ps.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public static boolean removeLeilao(int id) {
        String sql = "DELETE FROM leilao WHERE id=? ";
        try {
            PreparedStatement ps = ConectaAoBD.getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Nao foi remover do banco de dados");
            return false;
        }

    }

    public static boolean alteraLeilao(LeilaoModel leilaoModel, int id) {
        String sql = "UPDATE leilao SET item=?,precoInicial=?,situacao=? WHERE id=?";
        try {
            PreparedStatement ps = ConectaAoBD.getConexao().prepareStatement(sql);
            ps.setString(1, leilaoModel.getItem());
            ps.setDouble(2, leilaoModel.getPrecoInicial());
            ps.setString(3, leilaoModel.getSituacao().toString());
            ps.setInt(4, id);

            ps.executeUpdate();

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void main(String[] args) {
        //buscaLeilaoPorId(2);
    }

    public static LeilaoModel buscaLeilaoPorId(int idLeilao) {
        String sql = "select * from leilao where id = ?";
        try {
            PreparedStatement ps = ConectaAoBD.getConexao().prepareStatement(sql);
            ps.setInt(1, idLeilao);
            ResultSet rs = ps.executeQuery();
            Situacao situacao;
            while (rs.next()) {
                situacao = Situacao.valueOf(rs.getString(4));

               return  new LeilaoModel(rs.getInt(1), rs.getString(2), rs.getDouble(3), situacao);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

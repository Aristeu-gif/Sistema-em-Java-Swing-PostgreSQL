package br.edu.ifg.ado;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaAoBD {

    public static String status = "Não conectou...";

    public ConectaAoBD() {
    }

    public static Connection getConexao() {
        Connection connection = null; // atributo do tipo Connection
        try {
            // Carregando o JDBC Driver padrão
            String driverName = "org.postgresql.Driver";
            Class.forName(driverName);
            // Configurando a nossa conexão com um banco de dados//
            String serverName = "localhost:5432"; // caminho do servidor do BD
            String mydatabase = "postgres"; // nome do seu banco de dados
            String url = "jdbc:postgresql://" + serverName + "/" + mydatabase;
            String username = "postgres"; // nome de um usuário de seu BD
            String password = "@Aristeu123"; // sua senha de acesso
            connection = DriverManager.getConnection(url, username, password);
            // Testa sua conexão//
            if (connection != null) {
                status = "conectado";
            } else {
                status = null;
            }
            return connection;
        } catch (ClassNotFoundException e) { // Driver não encontrado
        	e.printStackTrace();
            // System.out.println("O driver expecificado nao foi encontrado.");
            return null;
        } catch (SQLException e) {
        	e.printStackTrace();
            // Não conseguindo se conectar ao banco
            // System.out.println("Nao foi possivel conectar ao Banco de Dados.");
            return null;
        }catch (Exception e){
        	e.printStackTrace();
        	return null;
        }
    }
    // Método que retorna o status da sua conexão//

    public static String statusConection() {
        return status;
    }
    // Método que fecha sua conexão//

    public static boolean FecharConexao() {
        try {
            ConectaAoBD.getConexao().close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    // Método que reinicia sua conexão//

    public static java.sql.Connection ReiniciarConexao() {
        FecharConexao();
        return ConectaAoBD.getConexao();
    }
}
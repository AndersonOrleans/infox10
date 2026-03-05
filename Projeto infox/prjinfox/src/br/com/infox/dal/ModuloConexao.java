package br.com.infox.dal;

import java.sql.*;

public class ModuloConexao {
	public static Connection conector() {
		Connection conexao = null;
		
		String url = "jdbc:mysql://localhost:3306/dbinfox?useSSL=false&serverTimezone=UTC";
		String user = "root";
		String password = "";
		
		try {
			conexao = DriverManager.getConnection(url, user, password);
			System.out.println("Conexão feita com sucesso!");
			return conexao;
		} catch (Exception e) {
			System.out.println("Erro na conexão com o banco de dados:");
			System.out.println(e.getMessage());
			return null;
		}
	}
}


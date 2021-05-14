package br.com.senai.controller.produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.dao.DataBaseConnection;

public class ListaProduto {
	
	private Connection connection;
	
	public ListaProduto() {
		connection = DataBaseConnection.getInstance().getConnection();
	}

	public ResultSet listarProdutos() {	
		PreparedStatement preparedStatement;
		try {
			String sql = "select * from produto";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			if(!resultSet.next()) {
				System.out.println("N�o possui dados cadastrados.");
				return null;
			}
			
			System.out.println("\n----- PRODUTOS CADASTRADOS -----\n");
			System.out.printf("| %2s | %15s | %8s | %4s | %9s |\n", "ID", "Produto", "Pre�o", "Qtd", "R$ Total");
			
			resultSet.previous();
			
			while(resultSet.next()) {
				System.out.printf("| %2s | %15s | R$%6.2f | %4s | %9s |\n",
						resultSet.getInt("codigoDoproduto"),
						resultSet.getString("nomeDoProduto"),
						resultSet.getDouble("precoDoProduto"),
						resultSet.getInt("quantidadeDeProduto"),
						resultSet.getDouble("saldoEmEstoque"));	
			}
			
			return resultSet;
		} catch (Exception e) {
			return null;
		}
	}
}

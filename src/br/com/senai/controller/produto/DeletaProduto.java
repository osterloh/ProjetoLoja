package br.com.senai.controller.produto;

import java.util.List;
import java.util.Scanner;

import br.com.senai.model.ProdutoModel;

public class DeletaProduto {
	
	Scanner entrada = new Scanner(System.in);
	ListaProduto listaProduto;

	public void removerProduto(List<ProdutoModel> produtos) {
		listaProduto = new ListaProduto();
		
		System.out.println("--- REMOVER PRODUTO ---");
		if (produtos.size() <= 0) {
			System.out.println("N�o possui produtos para serem removidos.");
			return;
		}

		listaProduto.listarProdutos();

		System.out.print("Informe o ID do produto a ser removido: ");
		int idDoProduto = entrada.nextInt();

		if (idDoProduto >= produtos.size()) {
			System.out.println("Este produto n�o foi cadastrado.");
			return;
		}

		produtos.remove(idDoProduto - 1);
	}
}

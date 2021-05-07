package br.com.senai.controller.produto;

import java.util.List;

import br.com.senai.model.ProdutoModel;

public class ListaProduto {

	public List<ProdutoModel> listarProdutos(List<ProdutoModel> produtos) {
		System.out.println("\n----- PRODUTOS CADASTRASDOS -----\n");
		System.out.printf("| %2s | %10s | %8s | %4s | %9s |\n", "ID", "Produto", "Preço", "Qtd", "R$ Total");

		produtos.forEach(produto -> {
			System.out.printf("| %2s | %10s | R$%6.2f | %4s | %9s |\n", produtos.indexOf(produto) + 1,
					produto.getNomeDoProduto(), produto.getPrecoDoProduto(), produto.getQuantidadeDeProduto(),
					produto.getSaldoEmEstoque());
		});

		return produtos;
	}
}

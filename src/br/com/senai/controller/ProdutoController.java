package br.com.senai.controller;

import java.util.List;
import java.util.Scanner;

import br.com.senai.model.ProdutoModel;

public class ProdutoController {

	private Scanner entrada;

	public ProdutoController() {
		entrada = new Scanner(System.in);
	}

	public int opcao() {
		System.out.print("> ");
		return entrada.nextInt();
	}
	
	public void menu() {
		System.out.println("\n--- MENU ---\n");
		System.out.println("1) Cadastrar itens");
		System.out.println("2) Listar estoque");
		System.out.println("3) Editar item");
		System.out.println("4) Remover item");
		System.out.println("5) Realizara venda");                                                                                      
		System.out.println("9) Sair do sistema");
		System.out.println("--------------------");
	}

	public ProdutoModel cadastrarProduto() {
		ProdutoModel produtoModel = new ProdutoModel();

		System.out.println("\n--- CADASTRAR ITENS ---\n");
		System.out.print("Produto: ");
		produtoModel.setNomeDoProduto(entrada.next());
		System.out.print("Preço: ");
		produtoModel.setPrecoDoProduto(entrada.nextDouble());
		System.out.print("Quantidade:");
		produtoModel.setQuantidadeDeProduto(entrada.nextInt());
		produtoModel.setSaldoEmEstoque(produtoModel.getQuantidadeDeProduto() * produtoModel.getPrecoDoProduto());
		
		return produtoModel;
	}
	
	public List<ProdutoModel> listarProdutos(List<ProdutoModel> produtos) {
		System.out.println("\n----- PRODUTOS CADASTRASDOS -----\n");
		System.out.printf("| %2s | %10s | %8s | %4s | %9s |\n", "ID", "Produto", "Preço", "Qtd", "R$ Total");
		
//		produtos.forEach(produto -> {
//			System.out.printf("| %10s | R$%6.2f | %4s | %9s |\n",
//					produto.getNomeDoProduto(),
//					produto.getPrecoDoProduto(),
//					produto.getQuantidadeDeProduto(),
//					produto.getSaldoEmEstoque());
//		});
		for (int i = 0; i < produtos.size(); i++) {
			System.out.printf("| %2s | %10s | R$%6.2f | %4s | %9s |\n",
					i + 1,
					produtos.get(i).getNomeDoProduto(),
					produtos.get(i).getPrecoDoProduto(),
					produtos.get(i).getQuantidadeDeProduto(),
					produtos.get(i).getSaldoEmEstoque());
		}
		
		return produtos;
	}
	
	public ProdutoModel editarProduto(List<ProdutoModel> produtos) {
		ProdutoModel produto = new ProdutoModel();
		int idDoProduto, indexDoCampo;
		
		if(produtos.size() <= 0) {
			System.out.println("Não possui produtos para serem editados.");
			return null;
		}
		
		listarProdutos(produtos);
		
		System.out.println("--- EDITAR DADOS DE PRODUTO ---");
		System.out.print("Informe o ID do produto: ");
		idDoProduto = entrada.nextInt() - 1;
		
		if(idDoProduto > produtos.size()) {
			System.out.println("Este produto não existe.");
			return null;
		}
		
		System.out.println("--- CAMPOS --");
		System.out.println("1) Nome do produto");
		System.out.println("2) Preço unitário");
		System.out.println("3) Quantidade");
		System.out.print("Informe o campo que deseja editar: ");
		indexDoCampo = entrada.nextInt();
		
		switch (indexDoCampo) {
		case 1:
			produto.setPrecoDoProduto(produtos.get(idDoProduto).getPrecoDoProduto());
			produto.setQuantidadeDeProduto(produtos.get(idDoProduto).getQuantidadeDeProduto());
			produto.setSaldoEmEstoque(produtos.get(idDoProduto).getSaldoEmEstoque());
			
			System.out.print("Informe o novo nome para o produto: ");
			produto.setNomeDoProduto(entrada.next());
			
			produtos.set(idDoProduto, produto);
			break;
		case 2:
			produto.setNomeDoProduto(produtos.get(idDoProduto).getNomeDoProduto());
			produto.setQuantidadeDeProduto(produtos.get(idDoProduto).getQuantidadeDeProduto());
			
			System.out.print("Informe o novo preço para o produto: ");
			produto.setPrecoDoProduto(entrada.nextDouble());
			produto.setSaldoEmEstoque(produtos.get(idDoProduto).getQuantidadeDeProduto() * produto.getPrecoDoProduto());
			
			produtos.set(idDoProduto, produto);
		case 3:
			produto.setNomeDoProduto(produtos.get(idDoProduto).getNomeDoProduto());
			produto.setPrecoDoProduto(produtos.get(idDoProduto).getPrecoDoProduto());
			
			System.out.print("Informe a quantidade do produto: ");
			produto.setQuantidadeDeProduto(entrada.nextInt());
			produto.setSaldoEmEstoque(produtos.get(idDoProduto).getPrecoDoProduto() * produto.getQuantidadeDeProduto());
			
			produtos.set(idDoProduto, produto);
			break;
		default:
			System.out.println("Opção inválida!!!");
			break;
		}
		
		return produto;
	}
	
	public void removerProduto(List<ProdutoModel> produtos) {
		System.out.println("--- REMOVER PRODUTO ---");
		if(produtos.size() <= 0) {
			System.out.println("Não possui produtos para serem removidos.");
			return;
		}
		
		listarProdutos(produtos);
		
		System.out.print("Informe o ID do produto a ser removido: ");
		int idDoProduto = entrada.nextInt();
		
		if(idDoProduto > produtos.size()) {
			System.out.println("Este produto não foi cadastrado.");
			return;
		}
		
		produtos.remove(idDoProduto - 1);
	}
}


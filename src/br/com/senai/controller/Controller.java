package br.com.senai.controller;

import java.util.List;
import java.util.Scanner;

import br.com.senai.model.CarrinhoModel;
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
		System.out.println("5) Adicionar ao carrinho");
		System.out.println("6) Listar itens no carrinho");
		System.out.println("9) Sair do sistema");
		System.out.println("--------------------");
	}

	public CarrinhoModel cadastrarItemNoCarrinho(List<ProdutoModel> produtos) {
		CarrinhoModel carrinhoModel = new CarrinhoModel();

		if (produtos.size() <= 0) {
			System.out.println("N�o h� produtos cadastrados.");
			return null;
		}

		listarProdutos(produtos);

		System.out.println("--- ADICIONAR ITEM NO CARRINHO ---");
		System.out.print("Informe o ID do produto: ");
		carrinhoModel.setIdDoProduto(entrada.nextInt());
		int idDoProduto = carrinhoModel.getIdDoProduto() - 1;

		if (carrinhoModel.getIdDoProduto() > produtos.size()) {
			System.out.println("Este produto n�o est� cadastrado.");
			return null;
		}

		System.out.print("Informe a quantidade desejada: ");
		carrinhoModel.setQuantidadeDeItensNoCarrinho(entrada.nextInt());

		if (carrinhoModel.getQuantidadeDeItensNoCarrinho() > produtos.get(idDoProduto).getQuantidadeDeProduto()) {
			System.out.println("Este produto n�o possui toda essa quantidade.");
			return null;
		}
		
		atualizarQuantidadeEValorTotal(produtos, carrinhoModel.getQuantidadeDeItensNoCarrinho(), idDoProduto);

		carrinhoModel.setProdutoModel(produtos.get(idDoProduto));
		carrinhoModel.setValorTotalPorItem(
				carrinhoModel.getQuantidadeDeItensNoCarrinho() * produtos.get(idDoProduto).getPrecoDoProduto());

		return carrinhoModel;
	}

	public List<ProdutoModel> atualizarQuantidadeEValorTotal(List<ProdutoModel> produtos, int quantidade, int idDoProduto){
		ProdutoModel produto = new ProdutoModel();
		produto.setQuantidadeDeProduto(
				produtos.get(idDoProduto).getQuantidadeDeProduto() - quantidade
		);
		produto.setSaldoEmEstoque(
				produtos.get(idDoProduto).getPrecoDoProduto() * produto.getQuantidadeDeProduto()
		);
		produto.setNomeDoProduto(produtos.get(idDoProduto).getNomeDoProduto());
		produto.setPrecoDoProduto(produtos.get(idDoProduto).getPrecoDoProduto());
		produtos.set(idDoProduto, produto);
		
		return produtos;
	}

	public List<CarrinhoModel> listarItensNoCarrinho(List<CarrinhoModel> itensNoCarrinho) {
		System.out.println("--- ITENS NO CARRINHO ---");
		System.out.printf("| %2s | %10s | %8s | %4s | %9s |\n", "ID", "Produto", "Pre�o", "Qtd", "R$ Total");

		if (itensNoCarrinho.size() <= 0) {
			return null;
		}

		itensNoCarrinho.forEach(item -> {
			System.out.printf("| %2s | %10s | R$%6.2f | %4s | R$%7.2f |\n", item.getIdDoProduto(),
					item.getProdutoModel().getNomeDoProduto(), item.getProdutoModel().getPrecoDoProduto(),
					item.getQuantidadeDeItensNoCarrinho(), item.getValorTotalPorItem());
		});

		double valorTotalDocarrinho = itensNoCarrinho.stream().mapToDouble(item -> item.getValorTotalPorItem()).sum();
		// CarrinhoModel::getValorTotalPorItem

		System.out.println("Valor total: R$" + valorTotalDocarrinho);

		return itensNoCarrinho;
	}

	public ProdutoModel cadastrarProduto() {
		ProdutoModel produtoModel = new ProdutoModel();

		System.out.println("\n--- CADASTRAR ITENS ---\n");
		System.out.print("Produto: ");
		produtoModel.setNomeDoProduto(entrada.next());
		System.out.print("Pre�o: ");
		produtoModel.setPrecoDoProduto(entrada.nextDouble());
		System.out.print("Quantidade:");
		produtoModel.setQuantidadeDeProduto(entrada.nextInt());
		produtoModel.setSaldoEmEstoque(produtoModel.getQuantidadeDeProduto() * produtoModel.getPrecoDoProduto());

		return produtoModel;
	}

	public List<ProdutoModel> listarProdutos(List<ProdutoModel> produtos) {
		System.out.println("\n----- PRODUTOS CADASTRASDOS -----\n");
		System.out.printf("| %2s | %10s | %8s | %4s | %9s |\n", "ID", "Produto", "Pre�o", "Qtd", "R$ Total");

		produtos.forEach(produto -> {
			System.out.printf("| %2s | %10s | R$%6.2f | %4s | %9s |\n", produtos.indexOf(produto) + 1,
					produto.getNomeDoProduto(), produto.getPrecoDoProduto(), produto.getQuantidadeDeProduto(),
					produto.getSaldoEmEstoque());
		});

		return produtos;
	}

	public ProdutoModel editarProduto(List<ProdutoModel> produtos) {
		ProdutoModel produto = new ProdutoModel();
		int idDoProduto, indexDoCampo;

		if (produtos.size() <= 0) {
			System.out.println("N�o possui produtos para serem editados.");
			return null;
		}

		listarProdutos(produtos);

		System.out.println("--- EDITAR DADOS DE PRODUTO ---");
		System.out.print("Informe o ID do produto: ");
		idDoProduto = entrada.nextInt() - 1;

		if (idDoProduto > produtos.size()) {
			System.out.println("Este produto n�o existe.");
			return null;
		}

		System.out.println("--- CAMPOS --");
		System.out.println("1) Nome do produto");
		System.out.println("2) Pre�o unit�rio");
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

			System.out.print("Informe o novo pre�o para o produto: ");
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
			System.out.println("Op��o inv�lida!!!");
			break;
		}

		return produto;
	}

	public void removerProduto(List<ProdutoModel> produtos) {
		System.out.println("--- REMOVER PRODUTO ---");
		if (produtos.size() <= 0) {
			System.out.println("N�o possui produtos para serem removidos.");
			return;
		}

		listarProdutos(produtos);

		System.out.print("Informe o ID do produto a ser removido: ");
		int idDoProduto = entrada.nextInt();

		if (idDoProduto > produtos.size()) {
			System.out.println("Este produto n�o foi cadastrado.");
			return;
		}

		produtos.remove(idDoProduto - 1);
	}
}

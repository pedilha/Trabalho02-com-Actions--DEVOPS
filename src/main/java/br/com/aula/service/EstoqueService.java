package br.com.aula.service;


import br.com.aula.model.Produto;
import br.com.aula.repository.ProdutoRepository;

public class EstoqueService {
    private final ProdutoRepository produtoRepository = new ProdutoRepository();

    public Produto cadastrarNovoProduto(String nome, String descricao, int quantidadeInicial) {
        // Critério de Aceite: A quantidade inicial não pode ser negativa[cite: 38].
        if (quantidadeInicial < 0) {
            throw new IllegalArgumentException("A quantidade inicial não pode ser negativa.");
        }

        // Critério de Aceite: Não deve ser possível cadastrar um produto com nome duplicado[cite: 37].
        if (produtoRepository.buscarPorNome(nome).isPresent()) {
            throw new IllegalArgumentException("Já existe um produto com o nome informado.");
        }

        Produto produtoParaSalvar = new Produto(null, nome, descricao, quantidadeInicial); // ID será gerado no repositório
        return produtoRepository.salvar(produtoParaSalvar);
    }
}
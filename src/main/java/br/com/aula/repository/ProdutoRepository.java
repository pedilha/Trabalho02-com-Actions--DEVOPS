package br.com.aula.repository;

import br.com.aula.model.Produto;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class ProdutoRepository {
    private final Map<Long, Produto> estoque = new HashMap<>();
    private final AtomicLong contadorId = new AtomicLong(0);


    public Produto salvar(Produto produto) {
        long novoId = contadorId.incrementAndGet();
        Produto novoProduto = new Produto(novoId, produto.getNome(), produto.getDescricao(), produto.getQuantidade());
        estoque.put(novoId, novoProduto);
        return novoProduto;
    }


    public Optional<Produto> buscarPorNome(String nome) {
        return estoque.values().stream()
                .filter(p -> p.getNome().equalsIgnoreCase(nome))
                .findFirst();
    }
    public List<Produto> buscarTodos() {
        return new ArrayList<>(estoque.values());
    }

    public Optional<Produto> buscarPorId(Long id) {
        return Optional.ofNullable(estoque.get(id));
    }

}
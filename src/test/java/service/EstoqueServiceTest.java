package service;


import br.com.aula.model.Produto;
import br.com.aula.service.EstoqueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EstoqueServiceTest {

    private EstoqueService estoqueService;

    @BeforeEach
    void setUp() {
        estoqueService = new EstoqueService();
    }

    @Test
    void deveCadastrarProdutoComSucesso() {
        Produto novoProduto = estoqueService.cadastrarNovoProduto("Caneta Azul", "Caneta esferográfica azul", 100);

        assertNotNull(novoProduto);
        assertNotNull(novoProduto.getId());
        assertEquals("Caneta Azul", novoProduto.getNome());
    }

    @Test
    void naoDeveCadastrarProdutoComNomeDuplicado() {
        estoqueService.cadastrarNovoProduto("Lápis Preto", "Lápis HB", 200);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            estoqueService.cadastrarNovoProduto("Lápis Preto", "Outra descrição", 50);
        });

        assertEquals("Já existe um produto com o nome informado.", exception.getMessage());
    }

    @Test
    void naoDeveCadastrarProdutoComQuantidadeNegativa() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            estoqueService.cadastrarNovoProduto("Borracha", "Borracha branca", -10);
        });

        assertEquals("A quantidade inicial não pode ser negativa.", exception.getMessage());
    }
}
package com.bomboniere.estoque.service;
import com.bomboniere.estoque.dto.*; import com.bomboniere.estoque.exception.EstoqueInsuficienteException; import com.bomboniere.estoque.model.*; import com.bomboniere.estoque.repository.MovimentacaoRepository; import org.junit.jupiter.api.*; import org.junit.jupiter.api.extension.ExtendWith; import org.mockito.*; import static org.junit.jupiter.api.Assertions.*; import static org.mockito.ArgumentMatchers.any; import static org.mockito.Mockito.*;
@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class MovimentacaoServiceTest {
 @Mock MovimentacaoRepository repo; @Mock ProdutoService produtos; @InjectMocks MovimentacaoService service;
 Produto produto;
 @BeforeEach void setup(){produto=new Produto();produto.setId(1L);produto.setNome("Chocolate");produto.setQuantidade(10);when(produtos.entidade(1L)).thenReturn(produto);}
 private void prepararPersistencia(){when(repo.save(any())).thenAnswer(i->{MovimentacaoEstoque m=i.getArgument(0);m.setId(1L);m.setDataMovimentacao(java.time.LocalDateTime.now());return m;});}
 @Test void adicionaQuantidadeNaEntrada(){prepararPersistencia();service.entrada(1L,new MovimentacaoRequest(5,"Fornecedor"));assertEquals(15,produto.getQuantidade());verify(repo).save(any());}
 @Test void retiraQuantidadeNaSaida(){prepararPersistencia();service.saida(1L,new MovimentacaoRequest(3,"Venda"));assertEquals(7,produto.getQuantidade());}
 @Test void impedeSaidaMaiorQueEstoque(){assertThrows(EstoqueInsuficienteException.class,()->service.saida(1L,new MovimentacaoRequest(11,"Venda")));verify(repo,never()).save(any());}
}

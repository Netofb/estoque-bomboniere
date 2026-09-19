package com.bomboniere.estoque.service;
import com.bomboniere.estoque.dto.*; import com.bomboniere.estoque.exception.*; import com.bomboniere.estoque.model.*; import com.bomboniere.estoque.repository.*;
import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional; import java.util.*;
@Service
public class ProdutoService {
 private final ProdutoRepository produtos; private final CategoriaService categorias;
 public ProdutoService(ProdutoRepository produtos,CategoriaService categorias){this.produtos=produtos;this.categorias=categorias;}
 public List<ProdutoResponse> listar(String nome,Long categoriaId){return produtos.buscar(nome,categoriaId).stream().map(this::dto).toList();}
 public List<ProdutoResponse> estoqueBaixo(){return produtos.encontrarEstoqueBaixo().stream().map(this::dto).toList();}
 public ProdutoResponse buscar(Long id){return dto(entidade(id));}
 @Transactional public ProdutoResponse criar(ProdutoRequest r){Produto p=new Produto(); preencher(p,r);return dto(produtos.save(p));}
 @Transactional public ProdutoResponse atualizar(Long id,ProdutoRequest r){Produto p=entidade(id);preencher(p,r);return dto(p);}
 @Transactional public void excluir(Long id){produtos.delete(entidade(id));}
 public Produto entidade(Long id){return produtos.findById(id).orElseThrow(()->new RecursoNaoEncontradoException("Produto não encontrado"));}
 private void preencher(Produto p,ProdutoRequest r){p.setNome(r.nome().trim());p.setDescricao(r.descricao());p.setPrecoCompra(r.precoCompra());p.setPrecoVenda(r.precoVenda());p.setQuantidade(r.quantidade());p.setEstoqueMinimo(r.estoqueMinimo());p.setCategoria(categorias.entidade(r.categoriaId()));if(r.ativo()!=null)p.setAtivo(r.ativo());}
 private ProdutoResponse dto(Produto p){return new ProdutoResponse(p.getId(),p.getNome(),p.getDescricao(),p.getPrecoCompra(),p.getPrecoVenda(),p.getPrecoVenda().subtract(p.getPrecoCompra()),p.getQuantidade(),p.getEstoqueMinimo(),new CategoriaResponse(p.getCategoria().getId(),p.getCategoria().getNome()),p.getAtivo(),p.getDataCadastro());}
}

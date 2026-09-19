package com.bomboniere.estoque.service;
import com.bomboniere.estoque.dto.*; import com.bomboniere.estoque.exception.*; import com.bomboniere.estoque.model.Categoria; import com.bomboniere.estoque.repository.*;
import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional; import java.util.*;
@Service
public class CategoriaService {
 private final CategoriaRepository categorias; private final ProdutoRepository produtos;
 public CategoriaService(CategoriaRepository categorias, ProdutoRepository produtos){this.categorias=categorias;this.produtos=produtos;}
 public List<CategoriaResponse> listar(){return categorias.findAll().stream().map(this::dto).toList();}
 public CategoriaResponse buscar(Long id){return dto(entidade(id));}
 @Transactional public CategoriaResponse criar(CategoriaRequest r){ validarNome(r.nome(),null); Categoria c=new Categoria(null,r.nome().trim()); return dto(categorias.save(c)); }
 @Transactional public CategoriaResponse atualizar(Long id,CategoriaRequest r){Categoria c=entidade(id);validarNome(r.nome(),id);c.setNome(r.nome().trim());return dto(c);}
 @Transactional public void excluir(Long id){if(produtos.countByCategoriaId(id)>0)throw new ConflitoException("Não é possível excluir categoria que possui produtos");categorias.delete(entidade(id));}
 public Categoria entidade(Long id){return categorias.findById(id).orElseThrow(()->new RecursoNaoEncontradoException("Categoria não encontrada"));}
 private void validarNome(String nome,Long id){if(categorias.existsByNomeIgnoreCase(nome.trim()) && (id==null || !entidade(id).getNome().equalsIgnoreCase(nome.trim())))throw new ConflitoException("Categoria já cadastrada");}
 private CategoriaResponse dto(Categoria c){return new CategoriaResponse(c.getId(),c.getNome());}
}

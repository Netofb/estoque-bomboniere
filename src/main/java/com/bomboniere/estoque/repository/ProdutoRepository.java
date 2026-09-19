package com.bomboniere.estoque.repository;
import com.bomboniere.estoque.model.Produto;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.*;
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
 @Query("select p from Produto p where (:nome is null or lower(p.nome) like lower(concat('%', :nome, '%'))) and (:categoriaId is null or p.categoria.id = :categoriaId)")
 List<Produto> buscar(@Param("nome") String nome, @Param("categoriaId") Long categoriaId);
 @Query("select p from Produto p where p.quantidade <= p.estoqueMinimo") List<Produto> encontrarEstoqueBaixo();
 long countByCategoriaId(Long categoriaId);
}

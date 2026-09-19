package com.bomboniere.estoque.repository;
import com.bomboniere.estoque.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface MovimentacaoRepository extends JpaRepository<MovimentacaoEstoque, Long> { List<MovimentacaoEstoque> findByProdutoIdOrderByDataMovimentacaoDesc(Long produtoId); List<MovimentacaoEstoque> findByTipoOrderByDataMovimentacaoDesc(TipoMovimentacao tipo); }

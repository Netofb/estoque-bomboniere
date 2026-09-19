package com.bomboniere.estoque.service;
import com.bomboniere.estoque.dto.*; import com.bomboniere.estoque.exception.EstoqueInsuficienteException; import com.bomboniere.estoque.model.*; import com.bomboniere.estoque.repository.MovimentacaoRepository;
import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional; import java.util.*;
@Service
public class MovimentacaoService {
 private final MovimentacaoRepository movimentacoes; private final ProdutoService produtos;
 public MovimentacaoService(MovimentacaoRepository movimentacoes,ProdutoService produtos){this.movimentacoes=movimentacoes;this.produtos=produtos;}
 public List<MovimentacaoResponse> listar(TipoMovimentacao tipo){return (tipo==null?movimentacoes.findAll():movimentacoes.findByTipoOrderByDataMovimentacaoDesc(tipo)).stream().map(this::dto).toList();}
 public List<MovimentacaoResponse> porProduto(Long produtoId){produtos.entidade(produtoId);return movimentacoes.findByProdutoIdOrderByDataMovimentacaoDesc(produtoId).stream().map(this::dto).toList();}
 @Transactional public MovimentacaoResponse entrada(Long produtoId,MovimentacaoRequest r){return registrar(produtoId,r,TipoMovimentacao.ENTRADA);}
 @Transactional public MovimentacaoResponse saida(Long produtoId,MovimentacaoRequest r){return registrar(produtoId,r,TipoMovimentacao.SAIDA);}
 private MovimentacaoResponse registrar(Long produtoId,MovimentacaoRequest r,TipoMovimentacao tipo){Produto p=produtos.entidade(produtoId);if(tipo==TipoMovimentacao.SAIDA && p.getQuantidade()<r.quantidade())throw new EstoqueInsuficienteException();p.setQuantidade(tipo==TipoMovimentacao.ENTRADA?p.getQuantidade()+r.quantidade():p.getQuantidade()-r.quantidade());MovimentacaoEstoque m=new MovimentacaoEstoque();m.setProduto(p);m.setTipo(tipo);m.setQuantidade(r.quantidade());m.setObservacao(r.observacao());return dto(movimentacoes.save(m));}
 private MovimentacaoResponse dto(MovimentacaoEstoque m){return new MovimentacaoResponse(m.getId(),m.getProduto().getId(),m.getProduto().getNome(),m.getTipo(),m.getQuantidade(),m.getDataMovimentacao(),m.getObservacao());}
}

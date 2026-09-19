package com.bomboniere.estoque.dto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
public record ProdutoResponse(Long id, String nome, String descricao, BigDecimal precoCompra, BigDecimal precoVenda, BigDecimal margemUnitario, Integer quantidade, Integer estoqueMinimo, CategoriaResponse categoria, Boolean ativo, LocalDateTime dataCadastro) {}

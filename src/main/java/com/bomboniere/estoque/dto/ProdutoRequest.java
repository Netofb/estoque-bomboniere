package com.bomboniere.estoque.dto;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
public record ProdutoRequest(@NotBlank String nome, @Size(max=1000) String descricao, @NotNull @PositiveOrZero BigDecimal precoCompra, @NotNull @PositiveOrZero BigDecimal precoVenda, @NotNull @PositiveOrZero Integer quantidade, @NotNull @PositiveOrZero Integer estoqueMinimo, @NotNull Long categoriaId, Boolean ativo) {}

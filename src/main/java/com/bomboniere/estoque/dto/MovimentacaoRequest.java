package com.bomboniere.estoque.dto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
public record MovimentacaoRequest(@NotNull @Positive(message="Quantidade deve ser maior que zero") Integer quantidade, @Size(max=500) String observacao) {}

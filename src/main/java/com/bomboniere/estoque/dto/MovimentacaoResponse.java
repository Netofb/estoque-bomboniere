package com.bomboniere.estoque.dto;
import com.bomboniere.estoque.model.TipoMovimentacao;
import java.time.LocalDateTime;
public record MovimentacaoResponse(Long id, Long produtoId, String produtoNome, TipoMovimentacao tipo, Integer quantidade, LocalDateTime dataMovimentacao, String observacao) {}

package com.bomboniere.estoque.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity @Getter @Setter @NoArgsConstructor
@Table(name="movimentacoes_estoque")
public class MovimentacaoEstoque {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(optional=false) @JoinColumn(name="produto_id") private Produto produto;
 @Enumerated(EnumType.STRING) @Column(nullable=false) private TipoMovimentacao tipo;
 @Column(nullable=false) private Integer quantidade; @Column(nullable=false, updatable=false) private LocalDateTime dataMovimentacao;
 @Column(length=500) private String observacao;
 @PrePersist void antesDeSalvar() { if(dataMovimentacao == null) dataMovimentacao = LocalDateTime.now(); }
}

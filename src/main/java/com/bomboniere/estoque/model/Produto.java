package com.bomboniere.estoque.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity @Getter @Setter @NoArgsConstructor
@Table(name = "produtos")
public class Produto {
 @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
 @Version private Long versao;
 @Column(nullable=false) private String nome; @Column(length=1000) private String descricao;
 @Column(nullable=false, precision=12, scale=2) private BigDecimal precoCompra;
 @Column(nullable=false, precision=12, scale=2) private BigDecimal precoVenda;
 @Column(nullable=false) private Integer quantidade; @Column(nullable=false) private Integer estoqueMinimo;
 @ManyToOne(optional=false) @JoinColumn(name="categoria_id") private Categoria categoria;
 @Column(nullable=false) private Boolean ativo = true; @Column(nullable=false, updatable=false) private LocalDateTime dataCadastro;
 @PrePersist void antesDeSalvar() { if (dataCadastro == null) dataCadastro = LocalDateTime.now(); if (ativo == null) ativo = true; }
}

package com.bomboniere.estoque.controller;
import com.bomboniere.estoque.dto.ResumoEstoqueResponse; import com.bomboniere.estoque.repository.*; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/dashboard")
public class DashboardController {private final ProdutoRepository produtos;private final CategoriaRepository categorias;public DashboardController(ProdutoRepository p,CategoriaRepository c){produtos=p;categorias=c;}@GetMapping("/resumo") public ResumoEstoqueResponse resumo(){long itens=produtos.findAll().stream().mapToLong(p->p.getQuantidade()).sum();return new ResumoEstoqueResponse(produtos.count(),categorias.count(),produtos.encontrarEstoqueBaixo().size(),itens);}}

package com.bomboniere.estoque.controller;
import com.bomboniere.estoque.dto.MovimentacaoResponse; import com.bomboniere.estoque.model.TipoMovimentacao; import com.bomboniere.estoque.service.MovimentacaoService; import org.springframework.web.bind.annotation.*; import java.util.*;
@RestController @RequestMapping("/api/movimentacoes")
public class MovimentacaoController { private final MovimentacaoService service;public MovimentacaoController(MovimentacaoService s){service=s;}@GetMapping public List<MovimentacaoResponse> listar(@RequestParam(required=false)TipoMovimentacao tipo){return service.listar(tipo);}@GetMapping("/produto/{id}") public List<MovimentacaoResponse> porProduto(@PathVariable Long id){return service.porProduto(id);} }

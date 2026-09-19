package com.bomboniere.estoque.repository;
import com.bomboniere.estoque.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CategoriaRepository extends JpaRepository<Categoria, Long> { boolean existsByNomeIgnoreCase(String nome); }

package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.CategoriaEntity;

public interface CategoriaService {
	

    Optional<CategoriaEntity> buscarCategoriaPorId(Long id);

    CategoriaEntity crearCategoria(CategoriaEntity categoria);

    CategoriaEntity actualizarCategoria(Long id, CategoriaEntity categoria);

    void eliminarCategoria(Long id);
    
    List<CategoriaEntity> listarCategorias();

}

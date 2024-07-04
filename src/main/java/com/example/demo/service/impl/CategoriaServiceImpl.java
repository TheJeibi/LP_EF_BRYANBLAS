package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CategoriaEntity;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<CategoriaEntity> listarCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    public Optional<CategoriaEntity> buscarCategoriaPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public CategoriaEntity crearCategoria(CategoriaEntity categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public CategoriaEntity actualizarCategoria(Long id, CategoriaEntity categoria) {
        Optional<CategoriaEntity> categoriaOptional = categoriaRepository.findById(id);
        if (categoriaOptional.isPresent()) {
            CategoriaEntity categoriaExistente = categoriaOptional.get();
            categoriaExistente.setNombre(categoria.getNombre()); // Actualizar nombre u otros campos según sea necesario
            // No es necesario modificar la lista de productos aquí, a menos que desees cambiarla explícitamente

            return categoriaRepository.save(categoriaExistente);
        }
        return null;
    }

    @Override
    public void eliminarCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }

    
}

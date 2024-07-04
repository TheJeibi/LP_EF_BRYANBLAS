package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ProductoEntity;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.service.ProductoService;

@Service
public class ProductoServiceImpl implements ProductoService{

	@Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<ProductoEntity> listarProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<ProductoEntity> buscarProductoPorId(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    public ProductoEntity crearProducto(ProductoEntity producto) {
        return productoRepository.save(producto);
    }

    public ProductoEntity actualizarProducto(Long id, ProductoEntity producto) {

        Optional<ProductoEntity> productoExistente = productoRepository.findById(id);
        if (productoExistente.isPresent()) {
            ProductoEntity productoActualizado = productoExistente.get();
            productoActualizado.setNombre(producto.getNombre());
            productoActualizado.setPrecio(producto.getPrecio());
            productoActualizado.setStock(producto.getStock());
            productoActualizado.setCategoria(producto.getCategoria()); // Actualizar la categoría si es necesario

            return productoRepository.save(productoActualizado);
        }
        return null;
    }

    @Override
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }

}

package com.example.demo.service;

import java.util.List;
import java.util.Optional;



import com.example.demo.entity.ProductoEntity;


public interface ProductoService {
	 List<ProductoEntity> listarProductos();

    Optional<ProductoEntity> buscarProductoPorId(Long id);

    ProductoEntity crearProducto(ProductoEntity producto);

    ProductoEntity actualizarProducto(Long id, ProductoEntity producto);

    void eliminarProducto(Long id);
}

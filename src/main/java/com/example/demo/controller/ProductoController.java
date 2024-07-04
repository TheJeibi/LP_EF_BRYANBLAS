package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.CategoriaEntity;
import com.example.demo.entity.ProductoEntity;
import com.example.demo.entity.UsuarioEntity;
import com.example.demo.service.CategoriaService;
import com.example.demo.service.ProductoService;
import com.example.demo.service.UsuarioService;

@Controller
public class ProductoController {
	
	 @Autowired
	    private ProductoService productoService;

	    @Autowired
	    private CategoriaService categoriaService;

	    @GetMapping("/listar")
	    public String listarProductos(Model model) {
	        List<ProductoEntity> productos = productoService.listarProductos();
	        model.addAttribute("productos", productos);
	        return "redirect:/menu";
	    }

	    @GetMapping("/crear")
	    public String mostrarFormularioCrear(Model model) {
	        model.addAttribute("producto", new ProductoEntity());
	        model.addAttribute("categorias", categoriaService.listarCategorias());
	        return "crear_producto";
	    }

	    @PostMapping("/guardar")
	    public String guardarProducto(@ModelAttribute("producto") ProductoEntity producto) {
	        productoService.crearProducto(producto);
	        return "redirect:/menu"; // Redirige correctamente al menú
	    }

	    @GetMapping("/editar/{id}")
	    public String mostrarFormularioEditar(@PathVariable("id") Long id, Model model) {
	        Optional<ProductoEntity> producto = productoService.buscarProductoPorId(id);
	        model.addAttribute("producto", producto);
	        model.addAttribute("categorias", categoriaService.listarCategorias());
	        return "editar_producto";
	    }

	    @PostMapping("/actualizar")
	    public String actualizarProducto(@ModelAttribute("producto") ProductoEntity producto) {
	        productoService.actualizarProducto(null, producto);
	        return "redirect:/menu";
	    }

	    @GetMapping("/eliminar/{id}")
	    public String eliminarProducto(@PathVariable("id") Long id) {
	        productoService.eliminarProducto(id);
	        return "redirect:/menu";
	    }


}

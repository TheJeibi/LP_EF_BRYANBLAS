package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.CategoriaEntity;
import com.example.demo.service.CategoriaService;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;


    @GetMapping("/buscar/{id}")
    public String buscarCategoriaPorId(@PathVariable Long id, Model model) {
        Optional<CategoriaEntity> categoria = categoriaService.buscarCategoriaPorId(id);
        if (categoria.isPresent()) {
            model.addAttribute("categoria", categoria.get());
            return "detalle_categoria"; // Nombre del archivo HTML/Thymeleaf
        } else {
            
            return "redirect:/categorias/crear"; // Ejemplo de redirección a la página de crear categoría
        }
    }

    @GetMapping("/crear")
    public String mostrarFormularioCrear(Model model) {
        model.addAttribute("categoria", new CategoriaEntity());
        return "crear_categoria"; // Nombre del archivo HTML/Thymeleaf
    }

    @PostMapping("/guardar")
    public String guardarCategoria(@ModelAttribute("categoria") CategoriaEntity categoria) {
        categoriaService.crearCategoria(categoria);
        return "redirect:/categorias/crear"; // Redirige a la página de creación de categoría o a otra página relevante
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Optional<CategoriaEntity> categoria = categoriaService.buscarCategoriaPorId(id);
        if (categoria.isPresent()) {
            model.addAttribute("categoria", categoria.get());
            return "editar_categoria"; // Nombre del archivo HTML/Thymeleaf
        } else {
            // Manejo de error o redirección según tu lógica
            return "redirect:/categorias/crear"; // Ejemplo de redirección a la página de crear categoría
        }
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarCategoria(@PathVariable Long id, @ModelAttribute("categoria") CategoriaEntity categoria) {
        categoria.setId(id); // Aseguramos que el ID de la categoría sea el mismo que el proporcionado
        categoriaService.actualizarCategoria(id, categoria);
        return "redirect:/categorias/crear"; // Redirige a la página de creación de categoría o a otra página relevante
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCategoria(@PathVariable Long id) {
        categoriaService.eliminarCategoria(id);
        return "redirect:/categorias/crear"; // Redirige a la página de creación de categoría o a otra página relevante
    }

}

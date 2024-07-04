package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.format.annotation.DateTimeFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.ProductoEntity;
import com.example.demo.entity.UsuarioEntity;
import com.example.demo.service.ProductoService;
import com.example.demo.service.UsuarioService;
import com.example.demo.utils.Utilitarios;

import javassist.expr.NewArray;

@Controller
public class UsuarioController {
	
	@Autowired
    private ProductoService productoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/registrar")
	public String showRegistrarUsuario(Model model) {
		
		model.addAttribute("usuario", new UsuarioEntity());
		return "registrar_usuario";
	}
	
	@PostMapping("/registrar")
	public String registrarUsuario(@ModelAttribute("usuario") UsuarioEntity usuarioEntity, Model model, 
			@RequestParam("foto")MultipartFile foto) {
		
	
		
		usuarioService.crearUsuario(usuarioEntity, model, foto);
		
		model.addAttribute("nombre_usuario", usuarioEntity.getNombre());
	    model.addAttribute("registroCorrecto", "¡Registro exitoso!");
		
		
		return "registrar_usuario";
	}
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("usuario", new UsuarioEntity());
		return "login";
	}
	
	@PostMapping("/login")
	public String login(UsuarioEntity usuarioEntity, Model model, HttpSession session) {
	    boolean usuarioValido = usuarioService.validarUsuario(usuarioEntity, session);
	    if (usuarioValido) {
            List<ProductoEntity> productos = productoService.listarProductos(); 
            model.addAttribute("productos", productos);
            model.addAttribute("nombre_usuario", usuarioEntity.getNombre());
            return "menu"; 
        }
	    
	    model.addAttribute("loginInvalido", "No existe el usuario");
	    model.addAttribute("usuario", new UsuarioEntity());
	    return "login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

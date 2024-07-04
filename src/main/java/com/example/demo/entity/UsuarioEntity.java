package com.example.demo.entity;

import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_usuario")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity {
	@Id
	private String correo;
	private String nombre;
	private String apellidos;
	private String password;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;
	private String urlImagen;
}

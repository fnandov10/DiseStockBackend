package com.disestock.disestockbackend.repository;

import com.disestock.disestockbackend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}

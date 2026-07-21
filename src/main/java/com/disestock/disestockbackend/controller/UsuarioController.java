package com.disestock.disestockbackend.controller;

import com.disestock.disestockbackend.model.Usuario;
import com.disestock.disestockbackend.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // GET - Listar todos los usuarios
    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    // GET - Buscar usuario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Integer id) {
        return usuarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST - Registrar un nuevo usuario
    @PostMapping
    public Usuario guardarUsuario(@Valid @RequestBody Usuario usuario) {
        return usuarioService.guardarUsuario(usuario);
    }

    // PUT - Actualizar un usuario existente
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(
            @PathVariable Integer id,
            @Valid @RequestBody Usuario usuario) {

        return usuarioService.buscarPorId(id)
                .map(usuarioExistente -> {
                    usuario.setId(id);
                    Usuario actualizado = usuarioService.guardarUsuario(usuario);
                    return ResponseEntity.ok(actualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE - Eliminar usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Integer id) {

        if (usuarioService.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
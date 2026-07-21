package com.disestock.disestockbackend.controller;

import com.disestock.disestockbackend.model.Inventario;
import com.disestock.disestockbackend.service.InventarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventario")
public class InventarioController {

    private final InventarioService inventarioService;

    public InventarioController(InventarioService inventarioService) {
        this.inventarioService = inventarioService;
    }

    // GET - Lista todos los materiales registrados
    @GetMapping
    public List<Inventario> listarInventario() {
        return inventarioService.listarInventario();
    }

    // GET - Lista los materiales con cantidad menor o igual al stock mínimo
    @GetMapping("/stock-bajo")
    public List<Inventario> listarStockBajo() {
        return inventarioService.listarStockBajo();
    }

    // GET - Busca un material específico por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Inventario> buscarPorId(@PathVariable Integer id) {

        return inventarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST - Registra un nuevo material
    @PostMapping
    public ResponseEntity<Inventario> crearInventario(
            @Valid @RequestBody Inventario inventario) {

        Inventario nuevo =
                inventarioService.guardarInventario(inventario);

        return ResponseEntity.ok(nuevo);
    }

    // PUT - Actualiza un material existente
    @PutMapping("/{id}")
    public ResponseEntity<Inventario> actualizarInventario(
            @PathVariable Integer id,
            @Valid @RequestBody Inventario inventario) {

        Inventario actualizado =
                inventarioService.actualizarInventario(id, inventario);

        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(actualizado);
    }

    // DELETE - Elimina un material existente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarInventario(
            @PathVariable Integer id) {

        boolean eliminado =
                inventarioService.eliminarInventario(id);

        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
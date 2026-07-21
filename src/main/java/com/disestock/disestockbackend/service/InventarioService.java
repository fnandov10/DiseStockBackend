package com.disestock.disestockbackend.service;

import com.disestock.disestockbackend.model.Inventario;
import com.disestock.disestockbackend.repository.InventarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventarioService {

    private final InventarioRepository inventarioRepository;

    public InventarioService(InventarioRepository inventarioRepository) {
        this.inventarioRepository = inventarioRepository;
    }

    // Lista todos los materiales registrados en el inventario
    public List<Inventario> listarInventario() {
        return inventarioRepository.findAll();
    }

    // Busca un material por su ID
    public Optional<Inventario> buscarPorId(Integer id) {
        return inventarioRepository.findById(id);
    }

    // Guarda un nuevo material y evita códigos duplicados
    public Inventario guardarInventario(Inventario inventario) {

        if (inventarioRepository.existsByCodigo(inventario.getCodigo())) {
            throw new IllegalArgumentException(
                    "Ya existe un material con el código " + inventario.getCodigo()
            );
        }

        return inventarioRepository.save(inventario);
    }

    // Actualiza un material existente
    public Inventario actualizarInventario(
            Integer id,
            Inventario datosActualizados) {

        Optional<Inventario> inventarioExistente =
                inventarioRepository.findById(id);

        if (inventarioExistente.isEmpty()) {
            return null;
        }

        Inventario inventario = inventarioExistente.get();

        // Si cambia el código, verifica que no pertenezca a otro material
        if (!inventario.getCodigo().equals(datosActualizados.getCodigo())
                && inventarioRepository.existsByCodigo(
                datosActualizados.getCodigo())) {

            throw new IllegalArgumentException(
                    "Ya existe un material con el código "
                            + datosActualizados.getCodigo()
            );
        }

        inventario.setNombre(datosActualizados.getNombre());
        inventario.setCodigo(datosActualizados.getCodigo());
        inventario.setCantidad(datosActualizados.getCantidad());
        inventario.setStockMinimo(datosActualizados.getStockMinimo());
        inventario.setEstado(datosActualizados.getEstado());

        return inventarioRepository.save(inventario);
    }

    // Elimina un material por su ID
    public boolean eliminarInventario(Integer id) {

        if (!inventarioRepository.existsById(id)) {
            return false;
        }

        inventarioRepository.deleteById(id);
        return true;
    }

    // Lista los materiales que alcanzaron o bajaron del stock mínimo
    public List<Inventario> listarStockBajo() {
        return inventarioRepository.buscarMaterialesConStockBajo();
    }
}
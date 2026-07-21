package com.disestock.disestockbackend.repository;

import com.disestock.disestockbackend.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Integer> {

    // Verifica si existe un material registrado con el mismo código
    boolean existsByCodigo(String codigo);

    // Obtiene materiales cuya cantidad es menor o igual al stock mínimo establecido
    @Query("SELECT i FROM Inventario i WHERE i.cantidad <= i.stockMinimo")
    List<Inventario> buscarMaterialesConStockBajo();
}
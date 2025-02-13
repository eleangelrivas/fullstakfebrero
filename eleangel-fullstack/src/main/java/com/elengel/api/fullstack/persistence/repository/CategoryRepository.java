package com.elengel.api.fullstack.persistence.repository;

import com.elengel.api.fullstack.persistence.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "SELECT COUNT(*) AS productCount, pr.category_id AS categoryId, ca.name AS categoryName " +
            "FROM product pr " +
            "JOIN category ca ON pr.category_id = ca.id " +
            "GROUP BY pr.category_id, ca.name", nativeQuery = true)
    List<Object[]> countProductsByCategory();

    @Query(value = "SELECT p.id, p.name, SUM(dv.cantidad) AS total_vendido " +
            "FROM detalle_venta dv " +
            "JOIN product p ON dv.producto_id = p.id " +
            "GROUP BY p.id, p.name " +
            "ORDER BY total_vendido DESC " +
            "LIMIT 3", nativeQuery = true)
    List<Object[]> findTopSellingProducts();


    @Query(value = "SELECT c.id, c.nombre, SUM(dv.total) AS total_ingresos " +
            "FROM detalle_venta dv " +
            "JOIN venta v ON dv.venta_id = v.id " +
            "JOIN cliente c ON v.cliente_id = c.id " +
            "GROUP BY c.id, c.nombre " +
            "ORDER BY total_ingresos DESC " +
            "LIMIT 3", nativeQuery = true)
    List<Object[]> findTopSpendingClients();


    @Query(value = "SELECT TO_CHAR(v.fecha, 'YYYY-MM') AS mes, SUM(dv.total) AS ingreso_total " +
            "FROM detalle_venta dv " +
            "JOIN venta v ON dv.venta_id = v.id " +
            "WHERE v.fecha >= (SELECT date_trunc('month', MAX(fecha)) - INTERVAL '2 months' FROM venta) " +
            "AND v.fecha <= (SELECT MAX(fecha) FROM venta) " +
            "GROUP BY mes " +
            "ORDER BY mes", nativeQuery = true)
    List<Object[]> findMonthlyRevenue();



}

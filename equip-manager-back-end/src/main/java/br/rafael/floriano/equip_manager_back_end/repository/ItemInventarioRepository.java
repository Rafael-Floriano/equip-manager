package br.rafael.floriano.equip_manager_back_end.repository;

import br.rafael.floriano.equip_manager_back_end.entity.InventoryItemEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemInventarioRepository extends JpaRepository<InventoryItemEntity, Long> {

    @Modifying
    @Query("""
    UPDATE InventoryItemEntity a SET a.status = Status.INATIVO WHERE a.codigoItem = :codigoItem
    """)
    void setStatusEqualsInativo(@Param("codigoItem") String codigoItem);

    Page<InventoryItemEntity> findAllByDescricaoContainingIgnoreCase(String descricao, Pageable pageable);

    boolean existsByCodigoItem(String codigoItem);

    @Modifying
    @Query("UPDATE InventoryItemEntity i SET i.excluido = true WHERE i.numeroDeSerie = :id")
    void softDeleteById(@Param("id") String id);

    Optional<InventoryItemEntity> findByNumeroDeSerie(String numeroDeSerie);

}

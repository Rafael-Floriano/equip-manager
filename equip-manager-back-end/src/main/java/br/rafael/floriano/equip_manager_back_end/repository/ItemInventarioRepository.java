package br.rafael.floriano.equip_manager_back_end.repository;

import br.rafael.floriano.equip_manager_back_end.entity.InventoryItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemInventarioRepository extends JpaRepository<InventoryItemEntity, Long> {
}

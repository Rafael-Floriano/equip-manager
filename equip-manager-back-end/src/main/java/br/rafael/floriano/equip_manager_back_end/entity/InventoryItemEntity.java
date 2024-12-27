package br.rafael.floriano.equip_manager_back_end.entity;

import jakarta.persistence.Table;

import java.time.LocalDate;

@Table(name = "inventory_item")
public class InventoryItemEntity extends BaseEntity {

    private String serialNumber;
    private String availability;
    private boolean status;
    private String description;
    private String localization;
    private LocalDate movementDate;

}

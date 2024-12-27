package br.rafael.floriano.equip_manager_back_end.entity;

import br.rafael.floriano.equip_manager_back_end.enums.Disponibilidade;
import br.rafael.floriano.equip_manager_back_end.enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Table(name = "inventory_item")
public class InventoryItemEntity extends BaseEntity {

    @Column(name = "numero_de_serie", nullable = false, length = 5)
    private String numeroDeSerie;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Disponibilidade disponibilidade;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false, length = 100)
    private String descricao;

    @Column(nullable = false, length = 250)
    private String localizacao;

    @Column(name = "data_movimentacao", nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataMovimentacao;

}

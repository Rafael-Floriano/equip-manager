package br.rafael.floriano.equip_manager_back_end.entity;

import br.rafael.floriano.equip_manager_back_end.enums.Disponibilidade;
import br.rafael.floriano.equip_manager_back_end.enums.Status;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "inventory_item")
public class InventoryItemEntity {

    @Id
    @Column(name = "codigo_item", unique = true, length = 7)
    private String codigoItem;

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

    public InventoryItemEntity(String codigoItem, String numeroDeSerie, String descricao, String localizacao) {
        this.codigoItem = codigoItem;
        this.numeroDeSerie = numeroDeSerie;
        this.descricao = descricao;
        this.dataMovimentacao = LocalDateTime.now();
        this.status = Status.ATIVO;
        this.disponibilidade = Disponibilidade.DISPONIVEL;
        this.localizacao = localizacao;
    }

    public InventoryItemEntity() {}

    @PrePersist
    void PrePersist() {
        this.dataMovimentacao = LocalDateTime.now();
    }

    @PreUpdate
    void preUpdate() {
        this.dataMovimentacao = LocalDateTime.now();
    }

    public String getCodigoItem() {
        return codigoItem;
    }

    public String getNumeroDeSerie() {
        return numeroDeSerie;
    }

    public Disponibilidade getDisponibilidade() {
        return disponibilidade;
    }

    public Status getStatus() {
        return status;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public LocalDateTime getDataMovimentacao() {
        return dataMovimentacao;
    }
}

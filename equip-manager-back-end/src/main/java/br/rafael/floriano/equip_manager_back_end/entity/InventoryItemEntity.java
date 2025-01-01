package br.rafael.floriano.equip_manager_back_end.entity;

import br.rafael.floriano.equip_manager_back_end.enums.Disponibilidade;
import br.rafael.floriano.equip_manager_back_end.enums.Status;
import br.rafael.floriano.equip_manager_back_end.mapper.DisponibilidadeConverter;
import br.rafael.floriano.equip_manager_back_end.mapper.StatusConverter;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name = "inventory_item")
@SQLRestriction("excluido = false")
public class InventoryItemEntity {

    @Column(name = "codigo_item", unique = true, length = 7)
    private String codigoItem;

    @Id
    @GeneratedValue(generator = "custom-id", strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "custom-id", strategy = "br.rafael.floriano.equip_manager_back_end.entity.CustomIdGenerator")
    @Column(name = "numero_de_serie", length = 5, updatable = false, insertable = false)
    private String numeroDeSerie;

    @Convert(converter = DisponibilidadeConverter.class)
    @Column(nullable = false)
    private Disponibilidade disponibilidade;

    @Convert(converter = StatusConverter.class)
    @Column(nullable = false)
    private Status status;

    @Column(nullable = false, length = 100)
    private String descricao;

    @Column(nullable = false, length = 250)
    private String localizacao;

    @Column(name = "data_movimentacao", nullable = false)
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataMovimentacao;

    @Column(nullable = false)
    private Boolean excluido;

    public InventoryItemEntity(String codigoItem, String descricao, String localizacao) {
        this.codigoItem = codigoItem;
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
        if (excluido == null) {
            excluido = false;
        }
    }

    @PreUpdate
    void preUpdate() {
        this.dataMovimentacao = LocalDateTime.now();
        if (excluido == null) {
            excluido = false;
        }
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

    public void setCodigoItem(String codigoItem) {
        this.codigoItem = codigoItem;
    }

    public void setNumeroDeSerie(String numeroDeSerie) {
        this.numeroDeSerie = numeroDeSerie;
    }

    public void setDisponibilidade(Disponibilidade disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public void setDataMovimentacao(LocalDateTime dataMovimentacao) {
        this.dataMovimentacao = dataMovimentacao;
    }

    public void setExcluido(Boolean excluido) {
        this.excluido = excluido;
    }
}

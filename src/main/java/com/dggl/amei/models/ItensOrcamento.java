package com.dggl.amei.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.juli.logging.Log;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ITENS_ORCAMENTO")
public class ItensOrcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Column(name = "VALOR_UNITARIO")
    private BigDecimal valorUnitario;

    @NotBlank
    @Column(name = "VALOR_TOTAL")
    private BigDecimal valorTotal;

    @Size(max = 500)
    @Column(name = "DESCRICAO")
    private String descricao;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "ORCAMENTO_ITENS", referencedColumnName = "id")
    private Orcamento itensOrcamento;

    @JsonIgnore
    @OneToMany(mappedBy = "itensOrcamento")
    private List<Servico> servicosItens = new ArrayList<>();


}

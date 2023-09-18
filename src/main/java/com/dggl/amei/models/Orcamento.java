package com.dggl.amei.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORCAMENTO")
public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @Size(max = 11)
    @Column(name = "TELEFONE_CLIENTE_ORCAMENTO")
    private String telefoneClienteOrcamento;

    @NotBlank
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    @Column(name = "HORARIO_GERACAO")
    private Instant dataEmissaoOrcamento;

    @NotBlank
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "DATA_VALIDADE")
    private LocalDate dataValidadeOrcamento;

    @NotBlank
    @Column(name = "VALOR_TOTAL")
    private BigDecimal valorTotalDoOrcamento;

    @Size(max = 500)
    @Column(name = "OBSERVACAOES")
    private String observacoesOrcamento;

    @NotBlank
    @ManyToOne
    @JoinColumn(name = "USUARIO_ORCAMENTO", referencedColumnName = "id")
    private User usuarioOrcamento;

    @ManyToOne
    @JoinColumn(name = "CLIENTE_ORCAMENTO", referencedColumnName = "id")
    private Clientes clienteOrcamento;

    @ManyToOne
    @JoinColumn(name = "ORDEM_ORCAMENTO", referencedColumnName = "id")
    private OrdemServico orcamentoOrdemServico;

    @JsonIgnore
    @OneToMany(mappedBy = "orcamentoItens")
    private List<ItensOrcamento> listaItensOrcamento = new ArrayList<>();
}

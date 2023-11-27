package com.dggl.amei.dtos.requests;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class EmissaoRelBaseRequestDTO {

    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Long codigoUsuario;

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(Long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }
}

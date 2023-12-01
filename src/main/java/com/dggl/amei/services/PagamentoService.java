package com.dggl.amei.services;

import com.dggl.amei.models.Pagamentos;
import com.dggl.amei.models.QOrdemServico;
import com.dggl.amei.models.enums.EnumPlanoAtivo;
import com.dggl.amei.repositories.OrdemServicoRepository;
import com.dggl.amei.repositories.PagamentosRepository;
import com.dggl.amei.repositories.UserRepository;
import com.stripe.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class PagamentoService {

    @Autowired
    PagamentosRepository pagamentosRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrdemServicoRepository ordemServicoRepository;

    public void registrarNovoPagamento(Invoice invoice) {
        var novoPagamento = new Pagamentos();

        var userFound = userRepository.findByEmail(invoice.getCustomerEmail());

        if (userFound.isEmpty()) return;

        var user = userFound.get();

        novoPagamento.setCustomerId(invoice.getCustomer());
        novoPagamento.setEmail(invoice.getCustomerEmail());
        novoPagamento.setNomeCartao(invoice.getCustomerName());
        novoPagamento.setValorPago(invoice.getAmountPaid());
        novoPagamento.setDataPagamento(LocalDateTime.now());
        novoPagamento.setUsuarioPagamento(user);

        pagamentosRepository.save(novoPagamento);

        var plano = invoice.getAmountPaid() == 10L ? EnumPlanoAtivo.MEI : EnumPlanoAtivo.ME;
        user.setPlano(plano);
        userRepository.save(user);
    }

    public String getCustomerId(Long userId) {
        var ultimoPagamento = pagamentosRepository.findFirstByUsuarioPagamento_IdOrderByDataPagamentoDesc(userId);

        return ultimoPagamento.getCustomerId();
    }

    public boolean faturamentoDentroDoLimite(Long userId, BigDecimal novoValor) {
        var user = userRepository.findById(userId);

        if (user.isEmpty()) return false;

        var userE = user.get();

        var pagamentosDoMes = ordemServicoRepository.findAll(QOrdemServico.ordemServico.usuarioOrdem.id.eq(userId)
                .and(QOrdemServico.ordemServico.dataEmissaoOrdemServico.month().eq(LocalDateTime.now().getMonthValue()))
                .and(QOrdemServico.ordemServico.dataEmissaoOrdemServico.year().eq(LocalDateTime.now().getYear())));

        var valorTotal = pagamentosDoMes.stream().map(i -> i.getValorTotal()).reduce(BigDecimal.ZERO, BigDecimal::add);
                //.mapToDouble(p -> p.getValorTotal().doubleValue()).sum();

        var novoValorTotal = valorTotal.add(novoValor);

        int resultado = 0;

        switch (userE.getPlano()) {
            case FREE -> {
                resultado = novoValorTotal.compareTo(new BigDecimal(1320));
            }

            case MEI ->  {
                resultado = novoValorTotal.compareTo(new BigDecimal(3000));
            }
        }

        if (resultado > 0) {
            return false;
        }

        return true;
    }
}

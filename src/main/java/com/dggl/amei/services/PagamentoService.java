package com.dggl.amei.services;

import com.dggl.amei.models.Pagamentos;
import com.dggl.amei.models.enums.EnumPlanoAtivo;
import com.dggl.amei.repositories.PagamentosRepository;
import com.dggl.amei.repositories.UserRepository;
import com.stripe.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PagamentoService {

    @Autowired
    PagamentosRepository pagamentosRepository;

    @Autowired
    UserRepository userRepository;

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
}

package com.dggl.amei.controllers;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static com.dggl.amei.AmeiApplication.STRIPE_API_KEY;

@RestController
@RequestMapping("/api/pagamentos")
public class Pagamentos extends AbstractController {



    @GetMapping("/novoPagamento")
    public ResponseEntity novoPagamento() throws StripeException {
        Stripe.apiKey = STRIPE_API_KEY;

        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(10L)
                .setCurrency("brl")
                .setAutomaticPaymentMethods(
                        PaymentIntentCreateParams.AutomaticPaymentMethods
                                .builder()
                                .setEnabled(true)
                                .build()
                )
                .build();

        PaymentIntent paymentIntent = PaymentIntent.create(params);

        Map<String, String> map = new HashMap<>();
        map.put("client_secret", paymentIntent.getClientSecret());

        return ResponseEntity.ok().body("Novo pagamento");
    }

}

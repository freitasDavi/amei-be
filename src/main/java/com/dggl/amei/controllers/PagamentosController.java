package com.dggl.amei.controllers;

import com.google.gson.JsonSyntaxException;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.net.ApiResource;
import com.stripe.param.PaymentIntentCreateParams;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.dggl.amei.AmeiApplication.STRIPE_API_KEY;

@RestController
@RequestMapping("/api/pagamentos")
public class PagamentosController extends AbstractController {


    @GetMapping("/novoPagamento")
    public ResponseEntity novoPagamento() throws StripeException {
        Stripe.apiKey = STRIPE_API_KEY;

        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(1000L)
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

        return ResponseEntity.ok().body(map);
    }

    @PostMapping("/webhook")
    public ResponseEntity webhook(@RequestBody String payload) {
        Event event = null;

        try {
            event = ApiResource.GSON.fromJson(payload, Event.class);
        } catch (JsonSyntaxException e) {
            System.out.println("Webhook error while parsing basic request.");
            return ResponseEntity.badRequest().body("Webhook error while parsing basic request.");
        }




        return ResponseEntity.ok().body("ok");
    }

}

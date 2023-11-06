package com.dggl.amei.controllers;

import com.google.gson.JsonSyntaxException;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
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

        System.out.println(event.getId());
        System.out.println(event.getType());
        System.out.println(event.getData().getObject().getClass());

        System.out.println("versão a API + " + Stripe.API_VERSION);
        System.out.println("versão do evento + " + event.getApiVersion());

        // Deserializae the nested object inside the event
        EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
        StripeObject stripeObject = null;
        if (dataObjectDeserializer.getObject().isPresent()) {
            stripeObject = dataObjectDeserializer.getObject().get();
        } else {
            // Deserialization failed, probably due to an API version mismatch.
            // Refer to the Javadoc documentation on `EventDataObjectDeserializer` for
            // instructions on how to handle this case, or return an error here.
        }

        switch (event.getType()) {
            case "payment_intent.succeeded":
                PaymentIntent paymentIntent = (PaymentIntent) stripeObject;
                System.out.println("Payment for " + paymentIntent.getAmount() + " succeeded");
                // Then define and call a method to handle the successful payment intent.
                // handlePaymentIntentSucceeded(paymentIntent);
                break;
            case "payment_method.attached":
                PaymentMethod paymentMethod = (PaymentMethod) stripeObject;
                // Then define and call a method to handle the successful attachment of a PaymentMethod.
                // handlePaymentMethodAttached(paymentMethod);
                break;
            default:
                System.out.println("Unhandled event type: " + event.getType());
        }

        return ResponseEntity.ok().body("Pagamento Recebido com sucesso!");
    }

}

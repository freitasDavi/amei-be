package com.dggl.amei.controllers;

import com.dggl.amei.configuration.security.services.UserDetailsImpl;
import com.dggl.amei.services.PagamentoService;
import com.google.gson.JsonSyntaxException;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.model.checkout.Session;
import com.stripe.net.ApiResource;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.dggl.amei.AmeiApplication.STRIPE_API_KEY;

@RestController
@RequestMapping("/api/pagamentos")
public class PagamentosController extends AbstractController {

    @Autowired
    PagamentoService service;

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

        /*
            Deserializae the nested object inside the event
        */

        EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
        StripeObject stripeObject = null;
        if (dataObjectDeserializer.getObject().isPresent()) {
            stripeObject = dataObjectDeserializer.getObject().get();
        } else {

            /*
                Deserialization failed, probably due to an API version mismatch.
                Refer to the Javadoc documentation on `EventDataObjectDeserializer` for
                instructions on how to handle this case, or return an error here.
            */

        }

        switch (event.getType()) {
            case "payment_intent.succeeded":
                PaymentIntent paymentIntent = (PaymentIntent) stripeObject;
                System.out.println("Payment for " + paymentIntent.getAmount() + " succeeded");

                /*
                    Then define and call a method to handle the successful payment intent.
                    handlePaymentIntentSucceeded(paymentIntent);
                */

                break;
            case "payment_method.attached":
                PaymentMethod paymentMethod = (PaymentMethod) stripeObject;

                /*
                   Then define and call a method to handle the successful attachment of a PaymentMethod.
                   handlePaymentMethodAttached(paymentMethod);
                */

                break;
            case "customer.subscription.created":
                System.out.println("Inscrição criada");
                break;
            case "customer.subscription.updated":
                System.out.println("Inscrição update");
                break;
            case "customer.subscription.deleted":
                System.out.println("Inscrição deletada");
                break;
            case "invoice.payment_succeeded":
                Invoice invoice = (Invoice) stripeObject;
                service.registrarNovoPagamento(invoice);
                System.out.println("Pagamento realizado com sucesso pelo cidadão " + invoice.getCustomerEmail());
                break;
            default:
                System.out.println("Unhandled event type: " + event.getType());
        }

        return ResponseEntity.ok().body("Pagamento Recebido com sucesso!");
    }

    @GetMapping("/novaAssinatura/{plano}")
    public ResponseEntity novaAssinatura (
            Authentication authentication,
            @PathVariable int plano) throws StripeException {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Stripe.apiKey = STRIPE_API_KEY;
        var planoEscolhido =  plano == 1 ?
                "price_1O9UTjEz6oa2bkpUtU8UNakW"
                : "price_1O9a2gEz6oa2bkpU3Uy6o72i";

        SessionCreateParams params =
                SessionCreateParams.builder()
                        .setMode(SessionCreateParams.Mode.SUBSCRIPTION)
                        .addLineItem(
                                SessionCreateParams.LineItem.builder()
                                        .setPrice(planoEscolhido)
                                        .setQuantity(1L)
                                        .build()
                        )
                        .setUiMode(SessionCreateParams.UiMode.EMBEDDED)
                        .setCustomerEmail(userDetails.getEmail())
                        .setReturnUrl("http://localhost:5173/pagamento/sucesso?session_id={CHECKOUT_SESSION_ID}")
                        .build();

        Session session = Session.create(params);

        Map<String, String> map = new HashMap<>();
        map.put("client_secret", session.getClientSecret());

        return ResponseEntity.ok().body(map);
    }


    @GetMapping("/customerPortal/{id}")
    public ResponseEntity getCustomerPortalSession (@PathVariable Long id) throws StripeException {
        Stripe.apiKey = STRIPE_API_KEY;
        var customerId = service.getCustomerId(id);

        com.stripe.param.billingportal.SessionCreateParams params =
                com.stripe.param.billingportal.SessionCreateParams.builder()
                        .setCustomer(customerId)
                        .setReturnUrl("http://localhost:5173/meuPerfil")
                        .build();

        com.stripe.model.billingportal.Session session = com.stripe.model.billingportal.Session.create(params);

        Map<String, String> map = new HashMap<>();
        map.put("portal_url", session.getUrl());

        return ResponseEntity.ok().body(map);
    }
}

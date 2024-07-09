package dev.jaffer.productService.controllers.webhooks;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhooks/stripe")
public class StripeWebHookController {

    @GetMapping("/")
    public void handleWebHookRequest() {
        // Handle the stripe webhook request
    }
}

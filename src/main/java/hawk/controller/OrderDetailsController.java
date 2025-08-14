package com.dryrun.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderDetailsController {

    private static final Map<String, Map<String, String>> ORDERS = Map.of(
            "o-101", Map.of("orderId", "o-101", "ownerId", "u-123", "item", "Cold Brew"),
            "o-202", Map.of("orderId", "o-202", "ownerId", "u-999", "item", "Latte")
    );

    @GetMapping("/{orderId}")
    public ResponseEntity<Map<String, String>> get(@PathVariable String orderId) {
        Map<String, String> order = ORDERS.get(orderId);
        if (order == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order);
    }
}

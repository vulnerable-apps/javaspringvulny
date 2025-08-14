package com.dryrun.demo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/directory")
@ConditionalOnProperty(value = "app.beta-endpoints", havingValue = "true", matchIfMissing = false)
public class ConsumerDirectoryController {

    private static final Map<String, Map<String, String>> DATA = Map.of(
            "u-123", Map.of("id", "u-123", "email", "alice@example.com"),
            "u-999", Map.of("id", "u-999", "email", "bob@example.com")
    );

    @GetMapping("/users/{userId}")
    public ResponseEntity<Map<String, String>> user(@PathVariable String userId) {
        Map<String, String> record = DATA.get(userId);
        if (record == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(record);
    }
}

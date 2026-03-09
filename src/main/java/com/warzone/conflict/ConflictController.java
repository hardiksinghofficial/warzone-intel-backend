package com.warzone.conflict;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/conflicts")
public class ConflictController {

    private final ConflictService service;
    public ConflictController(ConflictService service) { this.service = service; }

    @GetMapping
    public ResponseEntity<?> getAll() { return ok(service.getAll()); }

    @GetMapping("/active")
    public ResponseEntity<?> getActive() { return ok(service.getActive()); }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        return service.getById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/country/{code}")
    public ResponseEntity<?> getByCountry(@PathVariable String code) { return ok(service.getByCountry(code)); }

    private ResponseEntity<?> ok(List<?> data) {
        Map<String, Object> r = new LinkedHashMap<>();
        r.put("success", true); r.put("count", data.size()); r.put("data", data);
        return ResponseEntity.ok(r);
    }
}

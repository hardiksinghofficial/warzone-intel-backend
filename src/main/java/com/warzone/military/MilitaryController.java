package com.warzone.military;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController @RequestMapping("/api/military")
public class MilitaryController {
    private final MilitaryService svc;
    public MilitaryController(MilitaryService svc) { this.svc = svc; }

    @GetMapping public ResponseEntity<?> getAll() { return ok(svc.getAll()); }
    @GetMapping("/{code}") public ResponseEntity<?> get(@PathVariable String code) {
        return svc.getByCountry(code).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/top/{n}") public ResponseEntity<?> top(@PathVariable int n) { return ok(svc.getTopN(n)); }
    @GetMapping("/compare") public ResponseEntity<?> cmp(@RequestParam String a, @RequestParam String b) {
        return ResponseEntity.ok(svc.compare(a,b));
    }
    private ResponseEntity<?> ok(List<?> d) {
        Map<String,Object> r=new LinkedHashMap<>(); r.put("success",true); r.put("count",d.size()); r.put("data",d);
        return ResponseEntity.ok(r);
    }
}

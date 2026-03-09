package com.warzone.nuclear;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController @RequestMapping("/api/nuclear")
public class NuclearController {
    private final NuclearService svc;
    public NuclearController(NuclearService svc) { this.svc = svc; }

    @GetMapping public ResponseEntity<?> getAll() {
        List<NuclearArsenal> d=svc.getAll(); Map<String,Object> r=new LinkedHashMap<>();
        r.put("success",true); r.put("count",d.size()); r.put("data",d); return ResponseEntity.ok(r);
    }
    @GetMapping("/{code}") public ResponseEntity<?> get(@PathVariable String code) {
        return svc.getByCountry(code).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/stats") public ResponseEntity<?> stats() { return ResponseEntity.ok(svc.getGlobalStats()); }
}

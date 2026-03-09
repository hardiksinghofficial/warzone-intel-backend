package com.warzone.base;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController @RequestMapping("/api/bases")
public class BaseController {
    private final BaseService svc;
    public BaseController(BaseService svc) { this.svc = svc; }

    @GetMapping public ResponseEntity<?> all() { return ok(svc.getAll()); }
    @GetMapping("/country/{c}") public ResponseEntity<?> byCountry(@PathVariable String c) { return ok(svc.getByCountry(c)); }
    @GetMapping("/type/{t}") public ResponseEntity<?> byType(@PathVariable String t) { return ok(svc.getByType(t)); }
    @GetMapping("/overseas") public ResponseEntity<?> overseas() { return ok(svc.getOverseas()); }

    private ResponseEntity<?> ok(List<?> d) {
        Map<String,Object> r=new LinkedHashMap<>(); r.put("success",true); r.put("count",d.size()); r.put("data",d);
        return ResponseEntity.ok(r);
    }
}

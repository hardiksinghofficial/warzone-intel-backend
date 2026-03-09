package com.warzone.risk;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController @RequestMapping("/api/risk")
public class RiskController {
    private final RiskService svc;
    public RiskController(RiskService svc) { this.svc = svc; }

    @GetMapping public ResponseEntity<?> current() {
        return svc.getCurrent().map(ResponseEntity::ok).orElse(ResponseEntity.ok().build());
    }
    @GetMapping("/history") public ResponseEntity<?> history() {
        List<RiskScore> d=svc.getHistory(); Map<String,Object> r=new LinkedHashMap<>();
        r.put("success",true); r.put("count",d.size()); r.put("data",d); return ResponseEntity.ok(r);
    }
}

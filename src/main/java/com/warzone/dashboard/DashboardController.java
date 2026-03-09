package com.warzone.dashboard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/dashboard")
public class DashboardController {
    private final DashboardService svc;
    public DashboardController(DashboardService svc) { this.svc = svc; }

    @GetMapping public ResponseEntity<DashboardResponse> get() { return ResponseEntity.ok(svc.get()); }
}

package com.warzone.alert;

import com.warzone.conflict.Conflict;
import com.warzone.risk.RiskScore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class AlertService {

    private static final Logger log = LoggerFactory.getLogger(AlertService.class);
    private final SimpMessagingTemplate msg;

    public AlertService(SimpMessagingTemplate msg) { this.msg = msg; }

    public void broadcastConflict(Conflict c) {
        msg.convertAndSend("/topic/conflicts", c);
        log.debug("Broadcast: {}", c.getTitle());
    }

    public void broadcastRisk(RiskScore r) {
        msg.convertAndSend("/topic/risk", r);
        log.debug("Broadcast risk: {}", r.getOverallScore());
    }

    @Scheduled(fixedRate = 60000)
    public void heartbeat() {
        msg.convertAndSend("/topic/heartbeat",
            Map.of("type","HEARTBEAT","time",LocalDateTime.now().toString(),"status","LIVE"));
    }
}

package se.daga.mutant.adapters.input.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import se.daga.mutant.application.ports.in.StatsHumansUseCase;

import java.util.HashMap;
import java.util.Map;

/**
 * Stats humans controller (web adapter).
 *
 * @author davidgarcia
 */
@Component
@RestController
public class StatsHumansController {

    private final StatsHumansUseCase statsHumansUseCase;

    public StatsHumansController(StatsHumansUseCase statsHumansUseCase) {
        this.statsHumansUseCase = statsHumansUseCase;
    }

    /**
     * Get stats humans resource $HOST:$PORT/stats
     *
     * @return ResponseEntity<></>
     */

    @GetMapping(value = "/stats", produces = "application/json")
    public ResponseEntity<Map<String, Number>> validateMutantHuman() {
        var bodyResponse = new HashMap<String, Number>();
        var stats = statsHumansUseCase.stats();
        bodyResponse.put("count_mutant_dna", stats.getMutants());
        bodyResponse.put("count_human_dna", stats.getNoMutants());
        bodyResponse.put("ratio", stats.getRatio());
        return ResponseEntity.status(HttpStatus.OK).body(bodyResponse);
    }
}
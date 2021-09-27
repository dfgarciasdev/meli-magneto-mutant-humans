package se.daga.mutant.application.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import se.daga.mutant.application.models.StatsHumansModel;
import se.daga.mutant.application.ports.in.StatsHumansUseCase;
import se.daga.mutant.application.ports.out.StatsHumansPort;

/**
 * Stats humans service.
 *
 * @author davidgarcia.
 */
@Service
public class StatsHumansService implements StatsHumansUseCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(MutantHumanService.class);

    private final StatsHumansPort statsHumansPort;

    public StatsHumansService(StatsHumansPort statsHumansPort) {
        this.statsHumansPort = statsHumansPort;
    }

    /**
     * Get stats humans
     *
     * @return StatsHumansModel
     */
    @Override
    public StatsHumansModel stats() {
        var stats = statsHumansPort.getStats();
        LOGGER.info("mutants {}, no mutants {}, ratio {}", stats.getMutants(), stats.getNoMutants(), stats.getRatio());
        return statsHumansPort.getStats();
    }
}

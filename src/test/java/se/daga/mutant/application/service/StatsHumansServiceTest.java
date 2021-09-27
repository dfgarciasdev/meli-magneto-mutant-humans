package se.daga.mutant.application.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.daga.mutant.application.models.StatsHumansModel;
import se.daga.mutant.application.ports.in.StatsHumansUseCase;
import se.daga.mutant.application.ports.out.StatsHumansPort;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


/**
 * Unit test stats humans service.
 *
 * @author davidgarcia
 */
@ExtendWith(SpringExtension.class)
@Import(StatsHumansService.class)
class StatsHumansServiceTest {

    private static StatsHumansModel stats;

    @Autowired
    private StatsHumansUseCase statsHumansUseCase;

    @MockBean
    private StatsHumansPort statsHumansPort;

    @BeforeEach
    void setUp() {
        stats = new StatsHumansModel(1, 1, 1.0f);
        when(statsHumansPort.getStats()).thenReturn(stats);
    }

    @Test
    void stats() {
        var statsServiceResponse = statsHumansUseCase.stats();
        assertEquals(stats.getMutants(), statsServiceResponse.getMutants());
        assertEquals(stats.getNoMutants(), statsServiceResponse.getNoMutants());
        assertEquals(stats.getRatio(), statsServiceResponse.getRatio());
    }
}
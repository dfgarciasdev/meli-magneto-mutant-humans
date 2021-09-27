package se.daga.mutant.adapters.output.persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.daga.mutant.adapters.output.persistence.entities.HumanEntity;
import se.daga.mutant.adapters.output.persistence.mappers.HumanMapper;
import se.daga.mutant.adapters.output.persistence.repositories.HumanRepository;
import se.daga.mutant.application.models.SaveHumanModel;
import se.daga.mutant.application.ports.out.SaveHumanPort;
import se.daga.mutant.application.ports.out.StatsHumansPort;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Human persistence adapter test.
 *
 * @author davidgarcia
 */
@ExtendWith(SpringExtension.class)
@Import(HumanPersistenceAdapter.class)
class HumanPersistenceAdapterTest {

    @Autowired
    private SaveHumanPort saveHumanPort;

    @Autowired
    private StatsHumansPort statsHumansPort;

    @MockBean
    private HumanRepository humanRepository;

    @MockBean
    private HumanMapper humanMapper;

    @BeforeEach
    void setUp() {
        var listHumanEntity = Arrays.asList(new HumanEntity("AAAA", true),
                new HumanEntity("ABCD", false));
        when(humanRepository.findAll()).thenReturn(listHumanEntity);
        var humanEntitySaved = Optional.of(new HumanEntity("CCCC", true));
        when(humanRepository.findOne(any())).thenReturn(humanEntitySaved);
        saveHumanPort.saveHuman(new SaveHumanModel(String.join("-", "CCC"), true));
    }

    @Test
    void getStats() {
        var stats = statsHumansPort.getStats();
        assertEquals(1, stats.getMutants());
        assertEquals(1, stats.getNoMutants());
        assertEquals(1.0, stats.getRatio());
    }
}
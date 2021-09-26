package se.daga.mutant.adapters.output.persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import se.daga.mutant.adapters.output.persistence.entities.HumanEntity;
import se.daga.mutant.adapters.output.persistence.mappers.HumanMapper;
import se.daga.mutant.adapters.output.persistence.repositories.HumanRepository;
import se.daga.mutant.application.models.SaveHumanModel;
import se.daga.mutant.application.models.StatsHumansModel;
import se.daga.mutant.application.ports.out.SaveHumanPort;
import se.daga.mutant.application.ports.out.StatsHumansPort;

import java.util.stream.Collectors;


/**
 * Human persistence adapter.
 *
 * @author davidgarcia.
 */
@Component
public class HumanPersistenceAdapter implements SaveHumanPort, StatsHumansPort {
    private static final Logger LOGGER = LoggerFactory.getLogger(HumanPersistenceAdapter.class);
    private final HumanRepository humanRepository;
    private final HumanMapper humanMapper;


    public HumanPersistenceAdapter(HumanRepository humanRepository, HumanMapper humanMapper) {
        this.humanRepository = humanRepository;
        this.humanMapper = humanMapper;
    }

    /**
     * Save DNA and mutant status.
     *
     * @param saveHumanModel parameter.
     */
    @Override
    public void saveHuman(SaveHumanModel saveHumanModel) {
        humanRepository.findByDna(saveHumanModel.getDna())
                .ifPresentOrElse(humanEntity -> LOGGER.debug("DNA ({}) found in db", humanEntity.getDna())
                        , () -> humanRepository.save(humanMapper.modelToEntity(saveHumanModel)));

    }

    /**
     * Get stats humans.
     *
     * @return StatsHumansModel
     */
    @Override
    public StatsHumansModel getStats() {
        var partByIsMutant = humanRepository.findAll()
                .stream()
                .collect(Collectors.partitioningBy(HumanEntity::isMutant, Collectors.counting()));
        var noMutants = partByIsMutant.getOrDefault(Boolean.FALSE, 0L);
        var mutants = partByIsMutant.getOrDefault(Boolean.TRUE, 0L);
        var ratio = noMutants != 0 ? mutants / noMutants : 0L;
        return new StatsHumansModel(mutants, noMutants, ratio);
    }
}

package se.daga.mutant.application.ports.out;

import se.daga.mutant.application.models.StatsHumansModel;

/**
 * Stats humans port  {@link se.daga.mutant.adapters.output.persistence.HumanPersistenceAdapter}.
 *
 * @author davidgarcia.
 */
public interface StatsHumansPort {
    StatsHumansModel getStats();
}

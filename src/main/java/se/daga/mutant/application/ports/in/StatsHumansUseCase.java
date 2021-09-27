package se.daga.mutant.application.ports.in;

import se.daga.mutant.application.models.StatsHumansModel;

/**
 * Stats human use case port {@link se.daga.mutant.application.service.StatsHumansService}.
 *
 * @author davidgarcia.
 */
public interface StatsHumansUseCase {
    StatsHumansModel stats();
}

package se.daga.mutant.application.ports.out;

import se.daga.mutant.application.models.SaveHumanModel;

/**
 * Save human port  {@link se.daga.mutant.adapters.output.persistence.HumanPersistenceAdapter}.
 *
 * @author davidgarcia.
 */
public interface SaveHumanPort {
    void saveHuman(SaveHumanModel saveHumanModel);
}

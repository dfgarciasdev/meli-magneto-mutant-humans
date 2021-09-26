package se.daga.mutant.application.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import se.daga.mutant.application.exceptions.MutantHumanException;
import se.daga.mutant.application.ports.in.MutantHumanUseCase;
import se.daga.mutant.domain.Human;

/**
 * Mutant human service.
 *
 * @author davidgarcia.
 */
@Service
public class MutantHumanService implements MutantHumanUseCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(MutantHumanService.class);

    /**
     * Validate mutant human.
     *
     * @param dna array string.
     * @return true if is mutant.
     */
    @Override
    public boolean validateMutantHuman(String[] dna) {
        try {
            var isMutant = new Human().isMutant(dna);
            LOGGER.info("Is mutant human (dna:{}) ? {}", String.join(" ", dna), isMutant);
            return isMutant;
        } catch (Exception e) {
            throw new MutantHumanException("Error in mutant human service ->", e);
        }
    }
}

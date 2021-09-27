package se.daga.mutant.application.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import se.daga.mutant.application.exceptions.MutantHumanException;
import se.daga.mutant.application.models.SaveHumanModel;
import se.daga.mutant.application.ports.in.MutantHumanUseCase;
import se.daga.mutant.application.ports.out.SaveHumanPort;
import se.daga.mutant.domain.Human;

import java.util.Arrays;

/**
 * Mutant human service.
 *
 * @author davidgarcia.
 */
@Service
public class MutantHumanService implements MutantHumanUseCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(MutantHumanService.class);

    private final SaveHumanPort saveHumanPort;

    public MutantHumanService(SaveHumanPort saveHumanPort) {
        this.saveHumanPort = saveHumanPort;
    }

    /**
     * Validate mutant human.
     *
     * @param dnaInput array string.
     * @return true if is mutant.
     */
    @Override
    public boolean validateMutantHuman(String[] dnaInput) {
        try {
            var dna = Arrays.stream(dnaInput)
                    .map(String::toUpperCase)
                    .toArray(String[]::new);
            var isMutant = new Human().isMutant(dna);
            var keyDNA = String.join("-", dna);
            LOGGER.info("Is mutant human (dna:{}) ? {}", keyDNA, isMutant);
            saveHumanPort.saveHuman(new SaveHumanModel(keyDNA, isMutant));
            return isMutant;
        } catch (Exception e) {
            throw new MutantHumanException("Error in mutant human service ->", e);
        }
    }
}

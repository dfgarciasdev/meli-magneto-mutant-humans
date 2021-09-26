package se.daga.mutant.application.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.daga.mutant.application.exceptions.MutantHumanException;
import se.daga.mutant.application.ports.in.MutantHumanUseCase;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit test mutant human service.
 *
 * @author davidgarcia
 */
@ExtendWith(SpringExtension.class)
@Import(MutantHumanService.class)
class MutantHumanServiceTest {
    private static final String[] NO_MUTANT_DNA = {
            "ATGCGA",
            "CAGTGC",
            "TTATTT",
            "AGACGG",
            "GCGTCA",
            "TCACTG"};
    private static final String[] MUTANT_DNA = {
            "ATGCGA",
            "CAGTGC",
            "TTATGT",
            "AGAAGG",
            "CCCCTA",
            "TCACTG"};
    @Autowired
    private MutantHumanUseCase mutantHumanUseCase;

    @Test
    void MutantHuman() {
        assertTrue(mutantHumanUseCase.validateMutantHuman(MUTANT_DNA));
    }

    @Test
    void noMutantHuman() {
        assertFalse(mutantHumanUseCase.validateMutantHuman(NO_MUTANT_DNA));
    }

    @Test
    void nullDNA() {
        assertThrows(MutantHumanException.class, () -> mutantHumanUseCase.validateMutantHuman(null));
    }
}
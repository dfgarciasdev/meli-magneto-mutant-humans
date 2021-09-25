package se.daga.mutant.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests class human domain.
 *
 * @author davidgarcia
 */
class HumanTest {

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

    @Test
    void noMutantDNA() {
        var mutant = new Human();
        assertFalse(mutant.isMutant(NO_MUTANT_DNA));
    }

    @Test
    void mutantDNA() {
        var mutant = new Human();
        assertTrue(mutant.isMutant(MUTANT_DNA));
    }
}
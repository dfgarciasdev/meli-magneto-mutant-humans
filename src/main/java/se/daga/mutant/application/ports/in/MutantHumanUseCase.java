package se.daga.mutant.application.ports.in;

/**
 * Mutant human use case port {@link se.daga.mutant.application.service.MutantHumanService}.
 *
 * @author davidgarcia.
 */
public interface MutantHumanUseCase {
    boolean validateMutantHuman(String[] dna);
}

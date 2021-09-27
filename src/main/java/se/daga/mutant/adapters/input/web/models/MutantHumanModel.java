package se.daga.mutant.adapters.input.web.models;

import javax.validation.constraints.NotEmpty;

/**
 * Mutant human request model {@link se.daga.mutant.adapters.input.web.controllers.MutantHumanController}
 *
 * @author davidgarcia
 */
public final class MutantHumanModel {
    @NotEmpty
    private String[] dna;

    public String[] getDna() {
        return dna;
    }

    public void setDna(String[] dna) {
        this.dna = dna;
    }
}

package se.daga.mutant.application.models;

/**
 * Save human model.
 *
 * @author davidgarcia.
 */
public class SaveHumanModel {
    private String dna;
    private boolean mutant;

    public SaveHumanModel(String dna, boolean mutant) {
        this.dna = dna;
        this.mutant = mutant;
    }

    public String getDna() {
        return dna;
    }

    public void setDna(String dna) {
        this.dna = dna;
    }

    public boolean isMutant() {
        return mutant;
    }

    public void setMutant(boolean mutant) {
        this.mutant = mutant;
    }
}

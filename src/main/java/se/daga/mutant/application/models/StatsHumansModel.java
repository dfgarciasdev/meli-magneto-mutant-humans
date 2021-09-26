package se.daga.mutant.application.models;

/**
 * Stats humans model.
 *
 * @author davidgarcia.
 */
public final class StatsHumansModel {
    private final long mutants;
    private final long noMutants;
    private final float ratio;

    public StatsHumansModel(long mutants, long noMutants, float ratio) {
        this.mutants = mutants;
        this.noMutants = noMutants;
        this.ratio = ratio;
    }

    public long getMutants() {
        return mutants;
    }

    public long getNoMutants() {
        return noMutants;
    }

    public float getRatio() {
        return ratio;
    }

}

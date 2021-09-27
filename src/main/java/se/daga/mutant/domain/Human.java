package se.daga.mutant.domain;

import se.daga.mutant.domain.suffixtree.SuffixTree;

/**
 * Valid human DNA.
 *
 * @author davidgarcia
 */
public final class Human {

    /**
     * Validation if the human is a mutant.
     *
     * @param dna array
     * @return true if human is a mutant.
     */
    public boolean isMutant(final String[] dna) {
        return ExtractDataDNA
                .shuffledDataStream()
                .map(extractDataDNA -> extractDataDNA.getData(dna))
                .anyMatch(concatenateDataStrategy -> MutantDNAPatterns
                        .shuffledPatternsStream()
                        .anyMatch(mutantPattern -> !new SuffixTree(concatenateDataStrategy).searchText(mutantPattern.toString()).isEmpty()));
    }
}

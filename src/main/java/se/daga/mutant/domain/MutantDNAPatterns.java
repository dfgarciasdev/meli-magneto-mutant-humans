package se.daga.mutant.domain;

import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Mutant humans DNA patterns {@link Human}.
 *
 * @author davidgarcia
 */
public enum MutantDNAPatterns {
    AAAA, CCCC, TTTT, GGGG;

    /**
     * Get random DNA patterns for mutant humans.
     *
     * @return Stream<MutantDNAPatterns></>
     */
    public static Stream<MutantDNAPatterns> shuffledPatternsStream() {
        return Stream.of(MutantDNAPatterns.values()).collect(Collectors.collectingAndThen(Collectors.toList(), collected -> {
            Collections.shuffle(collected);
            return collected.stream();
        }));
    }
}

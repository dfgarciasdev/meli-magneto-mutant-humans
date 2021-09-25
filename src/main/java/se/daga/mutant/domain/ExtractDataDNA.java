package se.daga.mutant.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Strategies for extracting concatenated data from DNA by rows, columns, and obliques {@link Human}.
 *
 * @author davidgarcia
 */
public enum ExtractDataDNA {

    ROWS {
        @Override
        public String getData(String... dna) {
            return String.join(" ", dna);
        }
    },
    COLUMNS {
        @Override
        public String getData(String... dna) {
            var columns = new ArrayList<String>();
            for (var row = 0; row < dna.length; row++) {
                var column = new StringBuilder(dna.length);
                for (String s : dna) {
                    column.append(s.charAt(row));
                }
                columns.add(column.toString());
            }
            return String.join(" ", columns);
        }
    },
    OBLIQUES {
        @Override
        public String getData(String... dna) {
            var obliques = new ArrayList<String>();
            for (var z = 0; z < dna.length / 2; z++) {
                var oblique1 = new StringBuilder(dna.length);
                var oblique2 = new StringBuilder(dna.length);
                for (var w = 0; w < dna.length - z; w++) {
                    oblique1.append(dna[w].charAt(w + z));
                    if (z != 0) {
                        oblique2.append(dna[z + w].charAt(w));
                    }
                }
                if (oblique1.length() > 0) {
                    obliques.add(oblique1.toString());
                }
                if (oblique2.length() > 0) {
                    obliques.add(oblique2.toString());
                }
            }
            return String.join(" ", obliques);
        }
    };

    /**
     * Get the strategies of extracting concatenated data from DNA by rows, columns and obliques randomly.
     *
     * @return Stream<ExtractDataDNA></>
     */
    public static Stream<ExtractDataDNA> shuffledDataStream() {
        return Stream.of(ExtractDataDNA.values()).collect(Collectors.collectingAndThen(Collectors.toList(), collected -> {
            Collections.shuffle(collected);
            return collected.stream();
        }));
    }

    public abstract String getData(String... dna);
}

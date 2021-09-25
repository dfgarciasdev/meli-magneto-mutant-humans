package se.daga.mutant.domain;

import org.junit.jupiter.api.Test;
import se.daga.mutant.domain.suffixtree.SuffixTree;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * SuffixTree class unit tests
 *
 * @author davidgarcia
 */
class SuffixTreeTest {

    private static final String PATTERN = "AAAAA";

    @Test
    void matchText() {
        var suffixTree = new SuffixTree("AAAAAASDASDASDASDADSASDAAAAA");
        var countMatching = (long) suffixTree.searchText(PATTERN).size();
        assertEquals(3, countMatching);
    }

    @Test
    void noMatchText() {
        var suffixTree = new SuffixTree("ABCNDAWHGHJEWRTYJKRYUKTYUKERT");
        var countMatching = (long) suffixTree.searchText(PATTERN).size();
        suffixTree.searchText(PATTERN).forEach(System.out::println);
        assertEquals(0, countMatching);
    }
}
package se.daga.mutant.domain.suffixtree;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Fast pattern matching of strings {@link se.daga.mutant.domain.Human}.
 * Suffix Tree algorithm.
 *
 * @Copyright (c) 2017 Eugen Paraschiv
 */
public final class SuffixTree {

    private static final String WORD_TERMINATION = "$";
    private static final int POSITION_UNDEFINED = -1;
    private final Node root;
    private final String fullText;

    public SuffixTree(String text) {
        root = new Node("", POSITION_UNDEFINED);
        for (var i = 0; i < text.length(); i++) {
            addSuffix(text.substring(i) + WORD_TERMINATION, i);
        }
        fullText = text;
    }

    public List<String> searchText(String pattern) {
        var result = new ArrayList<String>();
        var nodes = getAllNodesInTraversePath(pattern, root, false);
        if (!nodes.isEmpty()) {
            var lastNode = nodes.get(nodes.size() - 1);
            if (lastNode != null) {
                List<Integer> positions = getPositions(lastNode);
                positions = positions.stream()
                        .sorted()
                        .collect(Collectors.toList());
                positions.forEach(m -> result.add((markPatternInText(m, pattern))));
            }
        }
        return result;
    }

    private void addSuffix(String suffix, int position) {
        var nodes = getAllNodesInTraversePath(suffix, root, true);
        if (nodes.isEmpty()) {
            addChildNode(root, suffix, position);
        } else {
            var lastNode = nodes.remove(nodes.size() - 1);
            var newText = suffix;
            if (!nodes.isEmpty()) {
                String existingSuffixUpToLastNode = nodes.stream()
                        .map(Node::getText)
                        .reduce("", String::concat);
                newText = newText.substring(existingSuffixUpToLastNode.length());
            }
            extendNode(lastNode, newText, position);
        }
    }

    private List<Integer> getPositions(Node node) {
        var positions = new ArrayList<Integer>();
        if (node.getText()
                .endsWith(WORD_TERMINATION)) {
            positions.add(node.getPosition());
        }
        for (var i = 0; i < node.getChildren()
                .size(); i++) {
            positions.addAll(getPositions(node.getChildren()
                    .get(i)));
        }
        return positions;
    }

    private String markPatternInText(Integer startPosition, String pattern) {
        var matchingTextLHS = fullText.substring(0, startPosition);
        var matchingText = fullText.substring(startPosition, startPosition + pattern.length());
        var matchingTextRHS = fullText.substring(startPosition + pattern.length());
        return matchingTextLHS + "[" + matchingText + "]" + matchingTextRHS;
    }

    private void addChildNode(Node parentNode, String text, int position) {
        parentNode.getChildren()
                .add(new Node(text, position));
    }

    private void extendNode(Node node, String newText, int position) {
        var currentText = node.getText();
        var commonPrefix = getLongestCommonPrefix(currentText, newText);

        if (!commonPrefix.equals(currentText)) {
            var parentText = currentText.substring(0, commonPrefix.length());
            var childText = currentText.substring(commonPrefix.length());
            splitNodeToParentAndChild(node, parentText, childText);
        }

        var remainingText = newText.substring(commonPrefix.length());
        addChildNode(node, remainingText, position);
    }

    private void splitNodeToParentAndChild(Node parentNode, String parentNewText, String childNewText) {
        var childNode = new Node(childNewText, parentNode.getPosition());
        if (!parentNode.getChildren()
                .isEmpty()) {
            while (!parentNode.getChildren()
                    .isEmpty()) {
                childNode.getChildren()
                        .add(parentNode.getChildren()
                                .remove(0));
            }
        }
        parentNode.getChildren()
                .add(childNode);
        parentNode.setText(parentNewText);
        parentNode.setPosition(POSITION_UNDEFINED);
    }

    private String getLongestCommonPrefix(String str1, String str2) {
        var compareLength = Math.min(str1.length(), str2.length());
        for (var i = 0; i < compareLength; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                return str1.substring(0, i);
            }
        }
        return str1.substring(0, compareLength);
    }

    private List<Node> getAllNodesInTraversePath(String pattern, Node startNode, boolean isAllowPartialMatch) {
        var nodes = new ArrayList<Node>();
        for (int i = 0; i < startNode.getChildren()
                .size(); i++) {
            var currentNode = startNode.getChildren()
                    .get(i);
            var nodeText = currentNode.getText();
            if (pattern.charAt(0) == nodeText.charAt(0)) {
                if (isAllowPartialMatch && pattern.length() <= nodeText.length()) {
                    nodes.add(currentNode);
                    return nodes;
                }

                var compareLength = Math.min(nodeText.length(), pattern.length());
                for (var j = 1; j < compareLength; j++) {
                    if (pattern.charAt(j) != nodeText.charAt(j)) {
                        if (isAllowPartialMatch) {
                            nodes.add(currentNode);
                        }
                        return nodes;
                    }
                }

                nodes.add(currentNode);
                if (pattern.length() > compareLength) {
                    var nodes2 = getAllNodesInTraversePath(pattern.substring(compareLength), currentNode, isAllowPartialMatch);
                    if (!nodes2.isEmpty()) {
                        nodes.addAll(nodes2);
                    } else if (!isAllowPartialMatch) {
                        nodes.add(null);
                    }
                }
                return nodes;
            }
        }
        return nodes;
    }
}


package se.daga.mutant.domain.suffixtree;

import java.util.ArrayList;
import java.util.List;

/**
 * Fast pattern matching of string.
 * Suffix Tree (Node) algorithm.
 *
 * @Copyright (c) 2017 Eugen Paraschiv
 */
public final class Node {

    private final List<Node> children;
    private String text;
    private int position;

    public Node(String text, int position) {
        this.text = text;
        this.children = new ArrayList<>();
        this.position = position;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public List<Node> getChildren() {
        return children;
    }

}

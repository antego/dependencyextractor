package com.github.antego.dependencyextractor;


import java.util.HashSet;
import java.util.Set;

public class LibNode {
    private LibNode parentNode;
    private LibNodeType type;
    private String name;
    private Set<LibNode> children = new HashSet<>();

    public LibNode(LibNode parentNode, LibNodeType type, String nodeName) {
        this.parentNode = parentNode;
        this.type = type;
        this.name = nodeName;
        if (parentNode != null) parentNode.addChildren(this);
    }

    public void addChildren(LibNode libNode) {
        children.add(libNode);
        libNode.setParentNode(this);
    }


    public LibNode getParentNode() {
        return parentNode;
    }

    public void setParentNode(LibNode parentNode) {
        this.parentNode = parentNode;
    }

    public LibNodeType getType() {
        return type;
    }

    public void setType(LibNodeType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<LibNode> getChildren() {
        return children;
    }
}

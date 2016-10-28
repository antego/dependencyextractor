package com.github.antego.dependencyextractor;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class LibNodeTest {
    @Test
    public void shouldCreateJarNode() {
        LibNode parentLibNode = new LibNode(null, LibNodeType.FOLDER, "parent");
        LibNode libNode = new LibNode(parentLibNode, LibNodeType.JAR, "nodeName");

        assertEquals("Wrong type", LibNodeType.JAR, libNode.getType());
        assertEquals("Wrong name", "nodeName", libNode.getName());
        assertEquals("Wrong parent node", parentLibNode, libNode.getParentNode());
    }

    @Test
    public void shouldAddChildrenToParent() {
        LibNode parentNode = new LibNode(null, null, null);
        LibNode childNode1 = new LibNode(parentNode, null, null);
        LibNode childNode2 = new LibNode(parentNode, null, null);

        assertEquals("Should be two children", 2, parentNode.getChildren().size());
        assertTrue("Should contain child1", parentNode.getChildren().contains(childNode1));
        assertTrue("Should contain child2", parentNode.getChildren().contains(childNode2));

    }

    @Test
    public void shouldAddParentWhenAddChildren() {
        LibNode parentNode = new LibNode(null, null, null);
        LibNode childNode = new LibNode(null, null, null);

        parentNode.addChildren(childNode);

        assertEquals("Wrong parent", parentNode, childNode.getParentNode());
    }
}

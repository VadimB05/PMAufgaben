package tree;

import card.Card;

/**
 * Node in a binary search tree.
 *
 * @param <T> Data-Type to store.
 */
public class Node<T extends Card> {
    private Node leftChild;
    private Node rightChild;
    private T data;

    /**
     * Create a new Node.
     *
     * @param data Vehicle to store
     */
    public Node(T data) {
        this.data = data;
    }

    /**
     * Add a new node as child of this node. Automatically inserted in the correct place below this
     * node.
     *
     * @param childData The data of the node.
     */
    public void addChild(T childData) {
        if (data.compareTo(childData) < 0) {
            if (rightChild != null) {
                rightChild.addChild(childData);
            } else {
                rightChild = new Node(childData);
            }
        } else if (leftChild != null) {
            leftChild.addChild(childData);
        } else {
            leftChild = new Node(childData);
        }
    }

    /**
     * @return The left child node of this node.
     */
    public final Node getLeftChild() {
        return leftChild;
    }
    /**
     * @return The right child node of this node.
     */
    public final Node getRightChild() {
        return rightChild;
    }
    /**
     * @return The stored data in this node.
     */
    public final T getData() {
        return data;
    }

    /**
     * @return This tree as dot formatted graph
     */
    public final String toDotGraph() {
        String dot = "digraph G {\n";
        dot += toDot();
        return dot += "}";
    }

    // help method for toDotGraph
    private final String toDot() {
        String dot = "";
        if (leftChild != null) {
            dot += data.getName() + "->" + leftChild.getData().getName() + "\n";
            dot += leftChild.toDot();
        }
        if (rightChild != null) {
            dot += data.getName() + "->" + rightChild.getData().getName() + "\n";
            dot += rightChild.toDot();
        }
        return dot;
    }
}

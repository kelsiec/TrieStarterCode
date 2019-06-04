public class Tree {
    private EntryNode root = new EntryNode(' ');

    public EntryNode getRoot() {
        return this.root;
    }

    public String toString() {
        return root.subtreeToString("", true);
    }
}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Trie {

    private EntryNode root = new EntryNode(' ');

    public EntryNode getRoot() {
        return this.root;
    }

    public String toString() {
        return root.subtreeToString("", true);
    }

    public void addWords(List<String> words) {
        for (String word: words) {
            insert(word);
        }
    }

    // This method contains a bug for you to fix
    public void insert(String word) {
        EntryNode current = this.getRoot();
        for (int i = 0; i < word.length(); i++) {
            char character = word.charAt(i);

            EntryNode child = current.getChild(character);
            if (child == null) {
                child = new EntryNode(character, i == word.length() - 1);
                current.addChild(child);
            }

            current = child;
        }
    }

    public void remove(String word) {
        EntryNode goingForward = this.getRoot();
        Stack<EntryNode> stack = new Stack<>();
        stack.push(goingForward);

        // Get to the end of the word
        for (int i = 0; i < word.length(); i++) {
            goingForward = goingForward.getChild(word.charAt(i));
            stack.push(goingForward);
        }

        // Remove the last letter
        EntryNode last = stack.pop();
        last.setTerminal(false);

        // Go back letter by letter to remove
        // This is missing logic to make sure that the child to remove isn't terminal,
        // and that it doesn't have any children that would also be incorrectly removed
        for (int i = word.length() - 1; i >= 0; i--) {
            EntryNode goingBack = stack.pop();
            EntryNode child = goingBack.getChild(word.charAt(i));
            if (child.isTerminal() || child.getNumChildren() > 0) {
                break;
            }
            goingBack.removeChild(word.charAt(i));
        }
    }

    public boolean contains(String potentialWord) {
        EntryNode current = this.getRoot();
        for (int i = 0; i < potentialWord.length(); i++) {
            char character = potentialWord.charAt(i);

            EntryNode child = current.getChild(character);
            if (child == null) {
                return false;
            }
            current = child;
        }

        return current.isTerminal();
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("src/main/resources/dictionary.txt"));
        List<String> words = new ArrayList<>();

        while (scan.hasNext()) {
            words.addAll(Arrays.asList(scan.nextLine().split(" ")));
        }

        Trie trie = new Trie();
        trie.addWords(words);
        System.out.println(trie);

        System.out.println();
        System.out.println("This test should report false:");
        System.out.println("Contains 's': " + trie.contains("s"));
        System.out.println("Contains 'bye': " + trie.contains("bye"));
        System.out.println("Contains 'bird': " + trie.contains("bird"));

        System.out.println();
        System.out.println("These tests should report true:");
        System.out.println("Contains 'she': " + trie.contains("she"));
        System.out.println("Contains 'sells': " + trie.contains("sells"));
        System.out.println("Contains 'sea': " + trie.contains("sea"));
        System.out.println("Contains 'shells': " + trie.contains("shells"));
        System.out.println("Contains 'by': " + trie.contains("by"));
        System.out.println("Contains 'the': " + trie.contains("the"));
        System.out.println("Contains 'shore': " + trie.contains("shore"));
        System.out.println("Contains 'shorebird': " + trie.contains("shorebird"));

        // These removes should produce a tree missing just those words
        trie.remove("shorebird");
        trie.remove("sells");
        System.out.println(trie);
    }
}

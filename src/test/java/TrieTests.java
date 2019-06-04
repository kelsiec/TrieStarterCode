import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TrieTests {
    Trie trie;
    List<String> dict = Arrays.asList(
            "shorebird", "she", "sells", "sea", "shells", "by", "the", "sea", "shore"
    );

    @Before
    public void setUpTrie() {
        trie = new Trie();
        trie.addWords(dict);
    }

    @Test
    public void testWordSubstringBeginning() {
        Assert.assertFalse(trie.toString(), trie.contains("s"));
    }

    @Test
    public void testWordSubstringEnd() {
        Assert.assertFalse(trie.toString(), trie.contains("bird"));
    }

    @Test
    public void testWordSuperString() {
        Assert.assertFalse(trie.toString(), trie.contains("bye"));
    }

    @Test
    public void testWordShorebird() {
        Assert.assertTrue(trie.toString(), trie.contains("shorebird"));
    }

    @Test
    public void testWordShe() {
        Assert.assertTrue(trie.toString(), trie.contains("she"));
    }

    @Test
    public void testWordSells() {
        Assert.assertTrue(trie.toString(), trie.contains("sells"));
    }

    @Test
    public void testWordSea() {
        Assert.assertTrue(trie.toString(), trie.contains("sea"));
    }

    @Test
    public void testWordShells() {
        Assert.assertTrue(trie.toString(), trie.contains("shells"));
    }

    @Test
    public void testWordBy() {
        Assert.assertTrue(trie.toString(), trie.contains("by"));
    }

    @Test
    public void testWordThe() {
        Assert.assertTrue(trie.toString(), trie.contains("the"));
    }

    @Test
    public void testWordShore() {
        Assert.assertTrue(trie.toString(), trie.contains("shore"));
    }
}

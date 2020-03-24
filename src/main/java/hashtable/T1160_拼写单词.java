package hashtable;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode-cn.com/problems/find-words-that-can-be-formed-by-characters/
 */
public class T1160_拼写单词 {
    /**
     * 【哈希表】
     * 执行用时 :64 ms, 在所有 Java 提交中击败了21.37% 的用户
     * 内存消耗 :42 MB, 在所有 Java 提交中击败了5.08%的用户
     */
    public int countCharacters(String[] words, String chars) {
        int ans = 0;
        if (words == null || words.length == 0 || chars == null || chars.length() == 0) return ans;
        // 统计chars的字符数
        char[] array = chars.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : array) {
            map.merge(c, 1, Integer::sum);
        }
        for (String word : words) {
            Map<Character, Integer> copy = new HashMap<>(map);
            boolean can = true; // 能拼写
            char[] letters = word.toCharArray();
            for (char letter : letters) {
                Integer count = copy.get(letter);
                if (count == null) {
                    can = false;
                    break;
                }
                if (count == 1) copy.remove(letter);
                copy.replace(letter, count - 1);
            }
            if (can) ans += letters.length;
        }
        return ans;
    }

    /**
     * 【使用数组模拟哈希表】
     * 题目明确说明所有字符串中都仅包含小写英文字母，故可以用长度为26的数组模拟哈希表
     * 执行用时 :12 ms, 在所有 Java 提交中击败了62.43% 的用户
     * 内存消耗 :42.2 MB, 在所有 Java 提交中击败了5.08%的用户
     */
    public int countCharacters2(String[] words, String chars) {
        int ans = 0;
        if (words == null || words.length == 0 || chars == null || chars.length() == 0) return ans;
        // 统计chars的字符数
        char[] array = chars.toCharArray();
        int[] hashTable = new int[26];
        int[] copy = new int[26];
        for (char c : array) {
            hashTable[c - 'a']++;
        }
        for (String word : words) {
            System.arraycopy(hashTable, 0, copy, 0, 26);
            boolean can = true; // 能拼写
            char[] letters = word.toCharArray();
            for (char letter : letters) {
                int count = copy[letter - 'a'];
                if (count == 0) {
                    can = false;
                    break;
                }
                copy[letter - 'a']--;
            }
            if (can) ans += letters.length;
        }
        return ans;
    }

    /**
     * 【优化】
     * 执行用时 :6 ms, 在所有 Java 提交中击败了89.17% 的用户
     * 内存消耗 :42.3 MB, 在所有 Java 提交中击败了5.08%的用户
     */
    public int countCharacters3(String[] words, String chars) {
        int ans = 0;
        if (words == null || words.length == 0 || chars == null || chars.length() == 0) return ans;
        // 统计chars的字符数
        char[] array = chars.toCharArray();
        int[] hashTable = new int[26];
        for (char c : array) {
            hashTable[c - 'a']++;
        }
        for (String word : words) {
            char[] wordArray = word.toCharArray();
            if (canSpell(wordArray, hashTable)) {
                ans += wordArray.length;
            }
        }
        return ans;
    }

    private boolean canSpell(char[] wordArray, int[] hashTable) {
        // 统计wordArray的字符数
        int[] array = new int[26];
        for (char c : wordArray) {
            int index = c - 'a';
            // 之前字符c的数目已经和字母表中的相等了，如果再加上现在遍历的，说明就已经大于字母表中的数目了
            if (array[index] == hashTable[index]) return false;
            array[index]++;
        }
        return true;
    }


    @Test
    public void test() {
        System.out.println(countCharacters(new String[]{"cat", "bt", "hat", "tree"}, "atach")); // 6
        System.out.println(countCharacters(new String[]{"hello", "world", "leetcode"}, "welldonehoneyr")); // 10

        System.out.println(countCharacters2(new String[]{"cat", "bt", "hat", "tree"}, "atach")); // 6
        System.out.println(countCharacters2(new String[]{"hello", "world", "leetcode"}, "welldonehoneyr")); // 10

        System.out.println(countCharacters3(new String[]{"cat", "bt", "hat", "tree"}, "atach")); // 6
        System.out.println(countCharacters3(new String[]{"hello", "world", "leetcode"}, "welldonehoneyr")); // 10
    }
}

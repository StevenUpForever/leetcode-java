package priority_queue;

import java.util.*;

public class TopKFrequentWords {

    //TAG: Uber
    //TAG: Priority queue

    /**
     * 692. Top K Frequent Words
     * Given a non-empty list of words, return the k most frequent elements.

     Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.

     Example 1:
     Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
     Output: ["i", "love"]
     Explanation: "i" and "love" are the two most frequent words.
     Note that "i" comes before "love" due to a lower alphabetical order.
     Example 2:
     Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
     Output: ["the", "is", "sunny", "day"]
     Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
     with the number of occurrence being 4, 3, 2 and 1 respectively.
     Note:
     You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
     Input words contain only lowercase letters.
     Follow up:
     Try to solve it in O(n log k) time and O(n) extra space.
     */

    /**
    Solution:
     Use hashMap filter strings with frequency
     sort map.values() by 1st frequency and then alphabetical order
     fetch top k to list and return

     Time: O(n) map process + O(nlogn) sort + O(k) fetch k = O(nlogn + k)
     Space: O(n) map + O(n) list + O(k) result list = O(n + k)
     */

    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Word> map = new HashMap<>();
        for (String str: words) {
            if (!map.containsKey(str)) {
                map.put(str, new Word(str));
            }
            Word val = map.get(str);
            val.frequency++;
            map.put(str, val);
        }
        List<Word> list = new ArrayList<>(map.values());
        list.sort(new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                if (o1.frequency == o2.frequency)
                    return alphabeticalCompare(o1.word, o2.word);
                return o2.frequency - o1.frequency;
            }
        });
        List<String> res = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            res.add(list.get(i).word);
        }
        return res;
    }

    /**
     * Priority queue solution
     */
    public List<String> topKFrequent2(String[] words, int k) {
        Map<String, Word> map = new HashMap<>();
        for (String str: words) {
            if (!map.containsKey(str)) {
                map.put(str, new Word(str));
            }
            Word val = map.get(str);
            val.frequency++;
            map.put(str, val);
        }
        PriorityQueue<Word> queue = new PriorityQueue<>(k, new Comparator<Word>() {
            @Override
            public int compare(Word o1, Word o2) {
                if (o1.frequency == o2.frequency)
                    return alphabeticalCompare(o1.word, o2.word);
                return o2.frequency - o1.frequency;
            }
        });
        for (Word word: map.values()) queue.offer(word);
        List<String> res = new ArrayList<>();
        while (!queue.isEmpty() && k > 0) {
            res.add(queue.poll().word);
            k--;
        }
        return res;
    }

    private int alphabeticalCompare(String a, String b) {
        int i = 0;
        while (i < a.length() && i < b.length() && a.charAt(i) == b.charAt(i)) i++;
        if (i < a.length() && i < b.length()) return a.charAt(i) - b.charAt(i);
        return i >= a.length() ? -1 : 1;
    }

    class Word {
        String word;
        int frequency;
        public Word(String word) {
            this.word = word;
            frequency = 0;
        }
    }

}

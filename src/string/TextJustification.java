package string;

import java.util.*;

public class TextJustification {

    //TAG: LinkedIn
    //TAG: Airbnb
    //TAG: array
    //TAG: String
    //Difficulty: Hard

    /**
     * 68. Text Justification
     * Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.

     You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.

     Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

     For the last line of text, it should be left justified and no extra space is inserted between words.

     For example,
     words: ["This", "is", "an", "example", "of", "text", "justification."]
     L: 16.

     Return the formatted lines as:
     [
     "This    is    an",
     "example  of text",
     "justification.  "
     ]
     Note: Each word is guaranteed not to exceed L in length.

     click to show corner cases.

     Corner Cases:
     A line other than the last line might contain only one word. What should you do in this case?
     In this case, that line should be left-justified.
      */

    /*
     * Solution:
     * For loop the words, have index i
     *      every time find until words sum(i + 1) + i (number of blanks) > maxWidth, do the add to StringBuilder step
     *          If the last line or this line can only has one word
     *              append all words with extra " " (as described about the last line)
     *              delete the last character for the last word, add maxWidth - builder.len spaces to the builder
     *          else
     *          average layout the words in the list, total blanks is maxWidth - sum(i)
     *              have the mold value, mold = sumBlanks%(i - 1), for loop the words, add sumBlanks/(i - 1) + 1
     *              until mold == 0, due to mold value must not over sumBlanks/(i - 1)
     *          Add String to list
     *      return the list
     *
     *  Time: O(2n) every word goes twice O(n)
     *  Space: O(maxWidth * n)
     */

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> list = new ArrayList<>();
        int index = 0;
        while (index < words.length) {
            //wordLen represent the total length of current words, lastIndex is the next index of the last correct index
            int wordLen = words[index].length(), lastIndex = index + 1;
            //If last Index is overflow or current total words length is over maxWidth
            while (lastIndex < words.length && wordLen + words[lastIndex].length() + lastIndex - index <= maxWidth) {
                wordLen += words[lastIndex].length();
                lastIndex++;
            }
            StringBuilder builder = new StringBuilder();
            //diff represent how many places need blanks be inserted
            int diff = lastIndex - index - 1;
            //If current line can only has one word, or this is the last line (need left justify and add one space between words)
            if (lastIndex == words.length || diff == 0) {
                //Append words with fixed one space
                for (int i = index; i < lastIndex; i++) {
                    builder.append(words[i]);
                    if (i < lastIndex - 1) builder.append(" ");
                }
                //Append rest of all blanks at the end
                for (int i = builder.length(); i < maxWidth; i++) builder.append(" ");
            } else {
                //blanks represent total blanks need be inserted for one space
                //mold represent the total extra blanks need be inserted from the first space, for example, if 10 spaces divided into 3 parts, they are 4, 3, 3
                int blanks = (maxWidth - wordLen)/diff, mold = (maxWidth - wordLen)%diff;
                for (int i = index; i < lastIndex; i++) {
                    builder.append(words[i]);
                    for (int j = 0; j < blanks && i < lastIndex - 1; j++) {
                        builder.append(" ");
                    }
                    //If have more extra space, add to current part in the current builder
                    if (mold-- > 0) builder.append(" ");
                }
            }
            list.add(builder.toString());
            index = lastIndex;
        }
        return list;
    }

}

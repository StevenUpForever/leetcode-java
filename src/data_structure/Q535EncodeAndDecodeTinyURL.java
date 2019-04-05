package data_structure;

import java.util.HashMap;
import java.util.Map;

public class Q535EncodeAndDecodeTinyURL {

    //TAG: Uber
    //Difficulty: Medium

    /**
     * 535. Encode and Decode TinyURL
     * Note: This is a companion problem to the System Design problem: Design TinyURL.
     TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk.

     Design the encode and decode methods for the TinyURL service. There is no restriction on how your encode/decode algorithm should work. You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.
     */

    //TODO: Solutions

    public class Codec {
        Map<String, String> map = new HashMap<>();
        // Encodes a URL to a shortened URL.
        public String encode(String longUrl) {
            String tinyUrl = "http://tinyurl.com/" + longUrl.hashCode();
            map.put(tinyUrl, longUrl);
            return tinyUrl;
        }

        // Decodes a shortened URL to its original URL.
        public String decode(String shortUrl) {
            return map.get(shortUrl);
        }
    }

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));

}

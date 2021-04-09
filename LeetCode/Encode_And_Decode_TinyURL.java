/* Noah Park

TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk.

Design the encode and decode methods for the TinyURL service. There is no restriction on how your encode/decode algorithm should work. You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.

*/

public class Codec {
    
    // Intuition: Generate a random extension of 7 letters. Utilize a continuous loop to ensure uniqueness.
    String random = "0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
    Random r = new Random();
    Map<String, String> map = new HashMap<>(), map2 = new HashMap<>();
    int i = 0;

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        String key = generate();
        while (map.containsKey(key)) key = generate();
        map.put(longUrl, "http://tinyurl.com/" + key);
        map2.put("http://tinyurl.com/" + key, longUrl);
        return "http://tinyurl.com/" + key;
    }
    
    // generate a random keycode for the following of tinyurl
    public String generate() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 7; i++) s.append(random.charAt(r.nextInt(62)));
        return s.toString();
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return map2.get(shortUrl);
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));

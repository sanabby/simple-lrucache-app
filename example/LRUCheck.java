import com.simple.lru.cache.LRUCache;

public class LRUCheck {

public static void main(String[] args) {
        LRUCache<String, Integer> cache = new LRUCache<String, Integer>();
        cache.put("ek", 1);
        cache.put("dho", 2);
        cache.put("theen", 3);
        cache.put("char", 4);
        cache.put("panch", 5);
        cache.put("chae", 6);
        cache.put("sath", 7);
        cache.put("aant", 8);
        cache.put("noau", 9);
        cache.put("dus", 10);
        cache.get("char");
        cache.display();
    }
}

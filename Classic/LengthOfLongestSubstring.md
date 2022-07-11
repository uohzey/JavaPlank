```java
//3.无重复字符的最长子串
//HashSet
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        // 二次遍历
        var set = new HashSet<Character>();
        int right = -1;
        int max = 0;
        for(int left = 0; left < n; left++){
            if(left != 0){
                set.remove(s.charAt(left - 1));
            }
            while(right + 1 < n && !set.contains(s.charAt(right + 1))){
                set.add(s.charAt(right + 1));
                right++;
            }
            max = Math.max(max, right - left + 1);
        }
        return max;
    }
}
```

```java
//HashMap
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Solution {
    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int n = s.length();
        int ans = 0;
        int left = 0;
        for(int right = 0; right < n; right++){
            char ch = s.charAt(right);
            if(map.containsKey(ch)){
                left = Math.max(map.get(ch) + 1, left);
            }
            ans = Math.max(ans, right - left + 1);
            map.put(ch, right);
        }
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int ans = lengthOfLongestSubstring(s);
        System.out.println(ans);
        sc.close();
    }
}
```


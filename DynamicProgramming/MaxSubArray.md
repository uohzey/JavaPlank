```java
class Solution {
    public int maxSubArray(int[] nums) {
        int n  = nums.length;
        int pre = 0;
        int ans = nums[0];
        for(int num : nums){
            pre = Math.max(pre + num, num);
            ans = Math.max(pre, ans);
        }
        return ans;
    }
}
```


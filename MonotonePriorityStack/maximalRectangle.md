```java
class Solution {
    public int maximalRectangle(char[][] matrix) {
        int ans = 0;
        int n = matrix.length;
        int m = matrix[0].length;
        int[] heights = new int[m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                heights[j] = matrix[i][j] == '1' ? heights[j] + 1 : 0;
            }
            ans = Math.max(ans, largestRectangleArea(heights));
        }
        return ans;
    }
    public int largestRectangleArea(int[] heights) {
        // 单调递增栈
        var stack = new ArrayDeque<Integer>();
        int ans = 0;
        // 哨兵
        int n = heights.length;
        int[] newHeights = new int[n + 2];
        newHeights[0] = 0;
        for(int i = 0; i < heights.length; i++) {
            newHeights[i + 1] = heights[i];
        }
        newHeights[n + 1] = 0;
        for(int i = 0; i < newHeights.length; i++) {
            while(!stack.isEmpty() && newHeights[i] < newHeights[stack.peek()]) {
                int cur = stack.pop();
                int left = stack.peek();
                int width = i - left - 1;
                int area = newHeights[cur] * width;
                ans = Math.max(ans, area);
            }
            stack.push(i);
        }
        return ans;
    }
}
```


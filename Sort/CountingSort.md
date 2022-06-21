```java
//数组长度为n,整数范围为k,对于k远小于n的场景,计数排序优于其他排序
//时间复杂度O(n+k),空间复杂度O(k)
class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        // 数字范围0-1000
        int[] counts = new int[1001];
        for(int num : arr1){
            counts[num]++;
        }
        int i = 0;
        for(int num : arr2){
            while(counts[num] > 0){
                arr1[i] = num;
                i++;
                counts[num]--;
            } 
        }
        for(int num = 0; num < 1001; num++){
            while(counts[num] > 0){
                arr1[i++] = num;
                counts[num]--;
            }
        }
        return arr1;
    }
}
```


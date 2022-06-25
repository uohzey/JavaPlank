```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        int target = nums.length - k;
        int left = 0;
        int right = nums.length - 1;
        int index = partition(nums, left, right);
        while(index != target){
            if(index < target){
                left = index + 1;
            }
            else{
                right = index - 1;
            }
            index = partition(nums, left, right);
        }
        // quickSort(nums, 0, nums.length - 1);
        return nums[index];
    }
    private void quickSort(int[] nums, int left, int right){
        if(right > left){
            int pivot = partition(nums, left, right);
            quickSort(nums, left, pivot - 1);
            quickSort(nums, pivot + 1, right);
        }
    }
    private int partition(int[] nums, int left, int right){
        // 随机选择优化很大
        int t = new Random().nextInt(right - left + 1) + left;
        swap(nums, t, right);
        int small = left - 1;
        for(int i = left; i < right; i++){
            if(nums[i] < nums[right]){
                small++;
                swap(nums, i, small);
            }
        }
        small++;
        swap(nums, small, right);
        return small;
    }
    private void swap(int[] nums, int left, int right){
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }
}
```


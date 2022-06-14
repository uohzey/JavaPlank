```
时间复杂度:O(2^n)
题目:输入一个不含重复数字的数据集合,找出所有子集.
```



```java
public List<List<Integer>> subsets(int[] nums){
    var result = new LinkedList<List<Integer>>();
    if(nums.length == 0){
        return result;
    }
    helper(nums, 0, new LinkedList<>(), result);
    return result;
}
private void helper(int[] nums, int index, LinkedList<Integer> subset, List<List<Integer>> result){
    if(index == nums.length){
        result.add(new LinkedList<>(subset));
    }
    else if(index < nums.length){
        helper(nums, index + 1, subset, result);
        
        subset.add(nums[index]);
        helper(nums, index + 1, subset, result);
        subset.removeLast();
    }
}
```


```java
class RandomizedSet {
    Map<Integer, Integer> numToLocation;
    List<Integer> nums;
    Random random;
    /** Initialize your data structure here. */
    public RandomizedSet() {
        numToLocation = new HashMap<>();
        nums = new ArrayList<>();
        random = new Random();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(numToLocation.containsKey(val)){
            return false;
        }
        numToLocation.put(val, nums.size());
        nums.add(val);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!numToLocation.containsKey(val)){
            return false;
        }
        int location = numToLocation.get(val);
        // put (finalVal, location);
        numToLocation.put(nums.get(nums.size() - 1), location);
        // remove (val, location);
        numToLocation.remove(val);
        
        nums.set(location, nums.get(nums.size() - 1));
        nums.remove(nums.size() - 1);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        int t = random.nextInt(nums.size());
        return nums.get(t);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
```


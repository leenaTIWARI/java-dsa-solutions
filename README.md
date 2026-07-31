# Java DSA Solutions — LeetCode

A collection of Java solutions to Data Structures & Algorithms problems solved on [LeetCode](https://leetcode.com/u/leenatiwari), covering core problem-solving patterns used in technical interviews and coding assessments.

## About

This repository documents my hands-on practice with Java and algorithmic problem-solving. Solutions are organized by topic for easy navigation, with a focus on clean, readable, and efficient code.

- **250+ problems solved** on LeetCode
- **Language:** Java
- **Focus areas:** Arrays, Strings, Linked Lists, Trees, Graphs, Dynamic Programming, Recursion & Backtracking, Sorting & Searching, Hashing, Stacks & Queues

Each solution file is named after the problem it solves (e.g., `TwoSum.java`, `ReverseLinkedList.java`) and includes:
- The problem approach as a brief comment
- Time and space complexity notes
- Clean, commented Java code

## Sample Solution Format

```java
// Problem: Two Sum
// Approach: Single-pass hash map to find complement in O(n)
// Time Complexity: O(n) | Space Complexity: O(n)

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        return new int[] {};
    }
}
```

## Connect

- **LeetCode:** [leetcode.com/u/leenatiwari](https://leetcode.com/u/leenatiwari)
- **GitHub:** [github.com/leenaTIWARI](https://github.com/leenaTIWARI)
- **LinkedIn:** [linkedin.com/in/leena-tiwari](https://linkedin.com/in/leena-tiwari)

---
*This repository is actively updated as I solve more problems and strengthen my grasp of data structures and algorithms.*

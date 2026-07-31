import java.util.*;

public class Leena_23SCSE1180192 {

    // Helper classes for Linked List, Binary Tree, Graph, and Huffman Coding
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) { this.val = val; }
    }

    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) { this.val = val; }
    }

    static class Graph {
        int V;
        List<List<Integer>> adj;
        Graph(int V) {
            this.V = V;
            adj = new ArrayList<>();
            for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        }
        void addEdge(int u, int v) {
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
    }

    static class HuffmanNode implements Comparable<HuffmanNode> {
        char data;
        int frequency;
        HuffmanNode left, right;
        HuffmanNode(char data, int frequency) {
            this.data = data;
            this.frequency = frequency;
        }
        public int compareTo(HuffmanNode node) {
            return this.frequency - node.frequency;
        }
    }

    // 1. Prefix Sum Array
    public static int rangeSumQuery(int[] arr, int L, int R) {
        int[] prefix = new int[arr.length];
        prefix[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            prefix[i] = prefix[i - 1] + arr[i];
        }
        if (L == 0) return prefix[R];
        return prefix[R] - prefix[L - 1];
    }

    // 2. Equilibrium Index
    public static int findEquilibriumIndex(int[] arr) {
        int totalSum = 0, leftSum = 0;
        for (int x : arr) totalSum += x;
        for (int i = 0; i < arr.length; i++) {
            totalSum -= arr[i];
            if (leftSum == totalSum) return i;
            leftSum += arr[i];
        }
        return -1;
    }

    // 3. Merge Two Sorted Linked Lists
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }
        if (l1 != null) current.next = l1;
        if (l2 != null) current.next = l2;
        return dummy.next;
    }

    // 4. Implement Two Stacks in an Array
    static class TwoStacks {
        int[] arr;
        int size;
        int top1, top2;

        TwoStacks(int n) {
            size = n;
            arr = new int[n];
            top1 = -1;
            top2 = size;
        }

        void push1(int x) {
            if (top1 < top2 - 1) {
                arr[++top1] = x;
            } else {
                System.out.println("Stack Overflow");
            }
        }

        void push2(int x) {
            if (top1 < top2 - 1) {
                arr[--top2] = x;
            } else {
                System.out.println("Stack Overflow");
            }
        }

        int pop1() {
            if (top1 >= 0) return arr[top1--];
            return -1;
        }

        int pop2() {
            if (top2 < size) return arr[top2++];
            return -1;
        }
    }

    // 5. Subsets
    public static List<List<Integer>> generateSubsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackSubsets(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private static void backtrackSubsets(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
        list.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            tempList.add(nums[i]);
            backtrackSubsets(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    // 6. Subarray Sum Equals K
    public static int subarraySum(int[] nums, int k) {
        int count = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int num : nums) {
            sum += num;
            if (map.containsKey(sum - k)) count += map.get(sum - k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    // 7. Jump Game
    public static boolean canJump(int[] nums) {
        int maxReach = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > maxReach) return false;
            maxReach = Math.max(maxReach, i + nums[i]);
        }
        return true;
    }

    // 8. Jump Game II
    public static int minJumps(int[] nums) {
        if (nums.length <= 1) return 0;
        int jumps = 0, currentEnd = 0, farthest = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;
                if (currentEnd >= nums.length - 1) break;
            }
        }
        return jumps;
    }

    // 9. Assign Cookies
    public static int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0, j = 0;
        while (i < g.length && j < s.length) {
            if (s[j] >= g[i]) i++;
            j++;
        }
        return i;
    }

    // 10. Diameter of a Binary Tree
    private static int maxDiameter = 0;
    public static int diameterOfBinaryTree(TreeNode root) {
        maxDiameter = 0;
        calculateHeight(root);
        return maxDiameter;
    }

    private static int calculateHeight(TreeNode node) {
        if (node == null) return 0;
        int leftHeight = calculateHeight(node.left);
        int rightHeight = calculateHeight(node.right);
        maxDiameter = Math.max(maxDiameter, leftHeight + rightHeight);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    // 11. Lowest Common Ancestor
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) return root;
        return left != null ? left : right;
    }

    // 12. Connected Components in an Undirected Graph
    public static int countConnectedComponents(Graph graph) {
        boolean[] visited = new boolean[graph.V];
        int count = 0;
        for (int v = 0; v < graph.V; ++v) {
            if (!visited[v]) {
                dfsComponent(v, visited, graph);
                count++;
            }
        }
        return count;
    }

    private static void dfsComponent(int v, boolean[] visited, Graph graph) {
        visited[v] = true;
        for (int n : graph.adj.get(v)) {
            if (!visited[n]) dfsComponent(n, visited, graph);
        }
    }

    // 13. Permutations
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackPermute(result, new ArrayList<>(), nums);
        return result;
    }

    private static void backtrackPermute(List<List<Integer>> list, List<Integer> tempList, int[] nums) {
        if (tempList.size() == nums.length) {
            list.add(new ArrayList<>(tempList));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (tempList.contains(nums[i])) continue;
                tempList.add(nums[i]);
                backtrackPermute(list, tempList, nums);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    // 14. House Robber
    public static int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        int prev1 = 0, prev2 = 0;
        for (int num : nums) {
            int tmp = prev1;
            prev1 = Math.max(prev2 + num, prev1);
            prev2 = tmp;
        }
        return prev1;
    }

    // 15. Huffman Coding
    public static void buildHuffmanTree(char[] charArray, int[] charfreq) {
        PriorityQueue<HuffmanNode> q = new PriorityQueue<>(charArray.length);
        for (int i = 0; i < charArray.length; i++) {
            HuffmanNode hn = new HuffmanNode(charArray[i], charfreq[i]);
            q.add(hn);
        }
        HuffmanNode root = null;
        while (q.size() > 1) {
            HuffmanNode x = q.peek(); q.poll();
            HuffmanNode y = q.peek(); q.poll();
            HuffmanNode f = new HuffmanNode('-', x.frequency + y.frequency);
            f.left = x;
            f.right = y;
            root = f;
            q.add(f);
        }
        printHuffmanCode(root, "");
    }

    private static void printHuffmanCode(HuffmanNode root, String s) {
        if (root.left == null && root.right == null && Character.isLetter(root.data)) {
            System.out.println(root.data + ":" + s);
            return;
        }
        printHuffmanCode(root.left, s + "0");
        printHuffmanCode(root.right, s + "1");
    }

    // 16. Minimum Number of Platforms
    public static int findPlatform(int[] arr, int[] dep) {
        Arrays.sort(arr);
        Arrays.sort(dep);
        int platforms = 1, result = 1;
        int i = 1, j = 0;
        while (i < arr.length && j < dep.length) {
            if (arr[i] <= dep[j]) {
                platforms++;
                i++;
            } else if (arr[i] > dep[j]) {
                platforms--;
                j++;
            }
            if (platforms > result) result = platforms;
        }
        return result;
    }

    // 17. Unique Paths
    public static int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] += dp[j - 1];
            }
        }
        return dp[n - 1];
    }

    // 18. Coin Change (Minimum Coins)
    public static int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[max];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    // 19. Climbing Stairs
    public static int climbStairs(int n) {
        if (n <= 1) return 1;
        int first = 1, second = 1;
        for (int i = 2; i <= n; i++) {
            int third = first + second;
            first = second;
            second = third;
        }
        return second;
    }

    // 20. Edit Distance
    public static int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[] dp = new int[n + 1];
        for (int j = 0; j <= n; j++) dp[j] = j;
        for (int i = 1; i <= m; i++) {
            int pre = dp[0];
            dp[0] = i;
            for (int j = 1; j <= n; j++) {
                int temp = dp[j];
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[j] = pre;
                } else {
                    dp[j] = Math.min(pre, Math.min(dp[j - 1], dp[j])) + 1;
                }
                pre = temp;
            }
        }
        return dp[n];
    }

    // MAIN METHOD RUNNING ALL TEST CASES
    public static void main(String[] args) {
        System.out.println("--- ETE Lab Exam Practice Solutions ---\n");

        // 1. Prefix Sum Array
        int[] arr1 = {1, 2, 3, 4, 5};
        System.out.println("1. Range Sum [1, 3]: " + rangeSumQuery(arr1, 1, 3));

        // 2. Equilibrium Index
        int[] arr2 = {-7, 1, 5, 2, -4, 3, 0};
        System.out.println("2. Equilibrium Index: " + findEquilibriumIndex(arr2));

        // 3. Merge Two Sorted Linked Lists
        ListNode l1 = new ListNode(1); l1.next = new ListNode(3);
        ListNode l2 = new ListNode(2); l2.next = new ListNode(4);
        ListNode merged = mergeTwoLists(l1, l2);
        System.out.print("3. Merged Linked List: ");
        while (merged != null) { System.out.print(merged.val + " "); merged = merged.next; }
        System.out.println();

        // 4. Implement Two Stacks in an Array
        TwoStacks ts = new TwoStacks(5);
        ts.push1(10); ts.push2(20); ts.push1(15);
        System.out.println("4. Pop Stack 1: " + ts.pop1() + ", Pop Stack 2: " + ts.pop2());

        // 5. Subsets
        int[] arr5 = {1, 2};
        System.out.println("5. Subsets: " + generateSubsets(arr5));

        // 6. Subarray Sum Equals K
        int[] arr6 = {1, 1, 1};
        System.out.println("6. Subarrays with sum 2: " + subarraySum(arr6, 2));

        // 7. Jump Game
        int[] arr7 = {2, 3, 1, 1, 4};
        System.out.println("7. Can reach end?: " + canJump(arr7));

        // 8. Jump Game II
        int[] arr8 = {2, 3, 1, 1, 4};
        System.out.println("8. Min Jumps needed: " + minJumps(arr8));

        // 9. Assign Cookies
        int[] children = {1, 2, 3}, cookies = {1, 1};
        System.out.println("9. Satisfied children: " + findContentChildren(children, cookies));

        // 10. Diameter of a Binary Tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2); root.right = new TreeNode(3);
        root.left.left = new TreeNode(4); root.left.right = new TreeNode(5);
        System.out.println("10. Diameter of Tree: " + diameterOfBinaryTree(root));

        // 11. Lowest Common Ancestor
        System.out.println("11. LCA of 4 and 5: " + lowestCommonAncestor(root, root.left.left, root.left.right).val);

        // 12. Connected Components in Graphs
        Graph g = new Graph(5);
        g.addEdge(0, 1); g.addEdge(2, 3);
        System.out.println("12. Connected Components count: " + countConnectedComponents(g));

        // 13. Permutations
        int[] arr13 = {1, 2, 3};
        System.out.println("13. Permutations sample count: " + permute(arr13).size());

        // 14. House Robber
        int[] houses = {2, 7, 9, 3, 1};
        System.out.println("14. Max money robbed: " + rob(houses));

        // 15. Huffman Coding
        System.out.println("15. Huffman Codes generated:");
        char[] charArray = { 'a', 'b', 'c' };
        int[] charfreq = { 5, 9, 12 };
        buildHuffmanTree(charArray, charfreq);

        // 16. Minimum Number of Platforms
        int[] arr16 = {900, 940, 950};
        int[] dep16 = {910, 1200, 1120};
        System.out.println("16. Min platforms required: " + findPlatform(arr16, dep16));

        // 17. Unique Paths
        System.out.println("17. Unique paths in 3x7 grid: " + uniquePaths(3, 7));

        // 18. Coin Change (Minimum Coins)
        int[] coins = {1, 2, 5};
        System.out.println("18. Min coins for amount 11: " + coinChange(coins, 11));

        // 19. Climbing Stairs
        System.out.println("19. Ways to climb 4 steps: " + climbStairs(4));

        // 20. Edit Distance
        System.out.println("20. Edit distance between 'horse' and 'ros': " + minDistance("horse", "ros"));
    }
}
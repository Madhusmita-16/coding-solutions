# Java Visitor Pattern

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

According to Wikipedia, a factory is simply an object that returns another object from some other method call, which is assumed to be "new".

In this problem, you are given an interface *Food*. There are two classes *Pizza* and *Cake* which implement the *Food* interface, and they both contain a method *getType*().

The main function in the *Main* class creates an instance of the *FoodFactory* class. The *FoodFactory* class contains a method *getFood(String)* that returns a new instance of *Pizza* or *Cake* according to its parameter.

You are given the partially completed code in the editor. Please complete the *FoodFactory* class.

**Sample Input 1**

	cake

**Sample Output 1**

	The factory returned class Cake
	Someone ordered a Dessert!

**Sample Input 2**

	pizza

**Sample Output 2**

	The factory returned class Pizza
	Someone ordered Fast Food!

**Input Format**

 

**Constraints**

 

**Output Format**

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-07T19:40:50.440Z  

```java

class SumInLeavesVisitor extends TreeVis {
     private int sum = 0;
     
    public int getResult() {
        return sum;
    }

    public void visitNode(TreeNode node) {
    
    }

    public void visitLeaf(TreeLeaf leaf) {
      	 sum += leaf.getValue();
    }
}

class ProductOfRedNodesVisitor extends TreeVis {
   
    private long product = 1;

    public int getResult() {
        return (int) product;
    }

     public void visitNode(TreeNode node) {
        if (node.getColor() == Color.RED) {
            product = (product * node.getValue()) % 1000000007;
        }
    }

    public void visitLeaf(TreeLeaf leaf) {
        if (leaf.getColor() == Color.RED) {
            product = (product * leaf.getValue()) % 1000000007;
        }
    }
}

class FancyVisitor extends TreeVis {

    private int evenDepthSum = 0;
    private int greenLeafSum = 0;

    public int getResult() {
        return Math.abs(evenDepthSum - greenLeafSum);
    }

    public void visitNode(TreeNode node) {
        if (node.getDepth() % 2 == 0) {
            evenDepthSum += node.getValue();
        }
    }

    public void visitLeaf(TreeLeaf leaf) {
        if (leaf.getColor() == Color.GREEN) {
            greenLeafSum += leaf.getValue();
        }
    }
}
public class Solution {
  
    public static Tree solve() {
                Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] values = new int[n];
        int[] colors = new int[n];

        for (int i = 0; i < n; i++) {
            values[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            colors[i] = sc.nextInt();
        }

        ArrayList<Integer>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt() - 1;
            int v = sc.nextInt() - 1;

            graph[u].add(v);
            graph[v].add(u);
        }

        return buildTree(0, -1, 0, values, colors, graph);
    }

    private static Tree buildTree(
            int current,
            int parent,
            int depth,
            int[] values,
            int[] colors,
            ArrayList<Integer>[] graph) {

        Color color;

        if (colors[current] == 0) {
            color = Color.RED;
        } else {
            color = Color.GREEN;
        }

        ArrayList<Integer> children = new ArrayList<>();

        for (int next : graph[current]) {
            if (next != parent) {
                children.add(next);
            }
        }

        if (children.isEmpty()) {
            return new TreeLeaf(
                values[current],
                color,
                depth
            );
        }

        TreeNode node = new TreeNode(
            values[current],
            color,
            depth
        );

        for (int child : children) {
            Tree childTree = buildTree(
                child,
                current,
                depth + 1,
                values,
                colors,
                graph
            );

            node.addChild(childTree);
        }

        return node;
    }


```

---

[View on HackerRank](https://www.hackerrank.com/challenges/java-factory/problem)
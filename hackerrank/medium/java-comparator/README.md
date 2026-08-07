# Java Comparator

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

Comparators are used to compare two objects. In this challenge, you'll create a comparator and use it to sort an array.

The *Player* class is provided for you in your editor. It has $2$ fields: a $name$ String and a $score$ integer.

Given an array of $n$ *Player* objects, write a comparator that sorts them in order of decreasing score; if $2$ or more players have the same score, sort those players alphabetically by name. To do this, you must create a *Checker* class that implements the *Comparator* interface, then write an *int compare(Player a, Player b)* method implementing the [Comparator.compare(T o1, T o2)](https://docs.oracle.com/javase/7/docs/api/java/util/Comparator.html#compare(T,%20T)) method.

**Input Format**

Input from stdin is handled by the locked stub code in the *Solution* class.	

The first line contains an integer, $n$, denoting the number of players. 	
Each of the $n$ subsequent lines contains a player's $name$ and $score$, respectively.

**Constraints**

- $0 \le score \le 1000$
- $2$ players can have the same name.
- Player names consist of lowercase English letters.

**Output Format**

You are not responsible for printing any output to stdout. The locked stub code in *Solution* will create a *Checker* object, use it to sort the *Player* array, and print each sorted element.

## Solution

**Language:** Java  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-08-07T19:26:50.911Z  

```java


class Checker implements Comparator<Player> {

    @Override
    public int compare(Player a, Player b) {

        // Higher score comes first
        if (a.score != b.score) {
            return b.score - a.score;
        }

        // Same score: alphabetical order by name
        return a.name.compareTo(b.name);
    }
}


```

---

[View on HackerRank](https://www.hackerrank.com/challenges/java-comparator/problem)
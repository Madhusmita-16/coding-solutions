# Querying XML Datastores with XPath - 3

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

The following table stores rows of information about pizza deliveries. The three columns correspond to the **Restaurant name**, **Crust**, **Delivery Area**. 
We convert this table into **Fourth Normal Form** and so we end up creating two tables, each with two columns and **N** rows. (Both the new tables have an equal number of rows)


    Restaurant	Crust		Delivery Area
	-------------------------------------------
	X Pizza		Thick		Whitefield
    X Pizza		Thick		Greenville
    X Pizza		Thick		Capital
    X Pizza		Stuffed		Whitefield
    X Pizza		Stuffed		Greenville
    X Pizza		Stuffed		Capital
    Papa Pizza	Thin		Capital
    Papa Pizza	Stuffed		Capital
    F1 Pizza	Thick		Whitefield
    F1 Pizza	Thick		Greenville
    F1 Pizza	Thin		Whitefield
    F1 Pizza	Thin		Greenville
    
In the text box below, enter the value of the integer **N**.      

**Input Format**

 

**Output Format**

## Solution

**Language:** Ruby  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-09-18T16:31:02.918Z  

```rb
require 'rexml/document'
include REXML

xmlText = ""

while line = gets()
    xmlText += line
end

doc = Document.new xmlText

XPath.match(doc, "collection/movie[popularity < 8]/format").each do |format|
    puts format.text
end


```

---

[View on HackerRank](https://www.hackerrank.com/challenges/database-normalization-10/problem)
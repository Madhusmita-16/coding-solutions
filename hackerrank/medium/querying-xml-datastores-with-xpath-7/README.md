# Querying XML Datastores with XPath - 7

![Difficulty](https://img.shields.io/badge/Difficulty-Medium-yellow)

## Problem

**XPath** is a very valuable tool, often used for querying XML databases. XPath queries (or close variants) are also used in the process of Web Scraping while retreiving data from structured XHTML-compliant web pages, specially those with tabulated data.  

A quick XPath tutorial is available [here](http://edutechwiki.unige.ch/en/XPath_tutorial_-_basics).  

**Provided XML Fragment and Task Description**  

Assume that you have been provided the fragment of XML, shown below. 
Your task is to write the XPath selector to compute the average popularity of movies, correct to 2 places of decimal.

The Ruby code for handling input, output and document construction has already been provided. You only need to fill up the required blanks as indicated in the template code provided. 
This challenge is agnostic to language-specific knowledge and you do not require to know ruby - as only the XPath selector needs to be completed by you.   

    <collection shelf="Classics">
    <movie title="The Enemy" shelf="A">
       <type>War, Thriller</type>
       <format>DVD</format>
       <year>2001</year>
       <rating>PG</rating>
       <popularity>10</popularity>
       <description>Talk about a war</description>
    </movie>
    <movie title="Transformers" shelf="B">
       <type>Science Fiction</type>
       <format>DVD</format>
       <year>1980</year>
       <rating>R</rating>
       <popularity>7</popularity>
       <description>Science Fiction</description>
    </movie>
       <movie title="Trigun" shelf="B">
       <type>Action</type>
       <format>DVD</format>
       <episodes>4</episodes>
       <rating>PG</rating>
       <popularity>10</popularity>
       <description>Quite a bit of action!</description>
    </movie>
    <movie title="Ishtar" shelf="A">
       <type>Comedy</type>
       <format>VHS</format>
       <rating>PG</rating>
       <popularity>2</popularity>
       <description>Boring</description>
    </movie>
    </collection>
    
**Expected Output** 

	7.25	
    
Your code will also be run against a hidden test case, which is similar in schema and format to the XML fragment shown above - only some of the values in the fields will be changed. This is to prevent the submission of hard-coded answers.     
	

**Input Format**

 

**Output Format**

## Solution

**Language:** Ruby  
**Runtime:** N/A  
**Memory:** N/A  
**Submitted:** 2026-09-18T16:25:46.259Z  

```rb
require 'rexml/document'
include REXML

xmlText = "" 

while line = gets()
    xmlText += line
end

doc = Document.new xmlText

values = XPath.match(doc, "collection/movie/popularity").map { |x| x.text.to_f }

average = values.sum / values.length

puts "%.2f" % average


```

---

[View on HackerRank](https://www.hackerrank.com/challenges/querying-xml-datastores-with-xpath-7/problem)
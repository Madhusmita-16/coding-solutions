require 'rexml/document'
include REXML

xmlText = ""

while line = gets()
    xmlText += line
end

doc = Document.new xmlText

puts doc.elements.each("collection/movie/@title")

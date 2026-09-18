require 'rexml/document'
include REXML

xmlText = ""

while line = gets()
    xmlText += line
end

doc = Document.new xmlText

puts XPath.match(doc, "string-length(collection/movie[2]/description)")

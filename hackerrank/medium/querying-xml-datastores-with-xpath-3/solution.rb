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


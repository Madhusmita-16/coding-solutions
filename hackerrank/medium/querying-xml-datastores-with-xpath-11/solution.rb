require 'rexml/document'
include REXML

xmlText = ""

while line = gets()
    xmlText += line
end

doc = Document.new xmlText

doc.elements.each("collection/movie[position() mod 2 = 1]") do |movie|
    puts movie.attributes["title"]
end


require 'rexml/document'
include REXML

xmlText = ""

while line = gets()
    xmlText += line
end

doc = Document.new xmlText

doc.elements.each("collection/movie/popularity") do |movie|
    puts movie.text
end


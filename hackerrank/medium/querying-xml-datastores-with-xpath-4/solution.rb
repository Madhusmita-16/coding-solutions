require 'rexml/document'
include REXML

xmlText = "" 

while line = gets()
    xmlText += line
end

doc = Document.new xmlText

puts doc.elements["collection/movie[@title='Trigun']/popularity/text()"]


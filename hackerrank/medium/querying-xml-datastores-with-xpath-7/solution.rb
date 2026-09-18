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


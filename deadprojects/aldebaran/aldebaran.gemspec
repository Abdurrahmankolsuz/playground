$LOAD_PATH.unshift File.expand_path('../lib', __FILE__)
require 'aldebaran/version'

Gem::Specification.new 'aldebaran', Aldebaran::VERSION do |s|
  s.description       = "Aldebaran is Web-development DSL micro-framework written with Mathematical Constructions"
  s.summary           = "DSL micro-framework with mathematical constructions"
  s.authors           = ["Mahmut Bulut", "Eren Kaplan"]
  s.homepage          = "http://www.resettek.com/aldebaran"
  s.files             = `git ls-files`.split("\n")
  s.test_files        = s.files.select { |p| p =~ /^test\/.*_test.rb/ }
  s.extra_rdoc_files  = s.files.select { |p| p =~ /^README/ } << 'LICENSE'
  s.rdoc_options      = %w[--line-numbers --inline-source --title aldebaran --main README.rdoc]

  s.add_dependency 'rack', '~> 1.3'
  s.add_dependency 'tilt', '~> 1.3'
end

#Gökte yılduz ellidur da
#Ellisuda bellidur
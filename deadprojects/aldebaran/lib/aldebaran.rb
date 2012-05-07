libdir = File.dirname(__FILE__)
$LOAD_PATH.unshift(libdir) unless $LOAD_PATH.include?(libdir)

require 'aldebaran/base'
require 'aldebaran/main'

enable :inline_templates

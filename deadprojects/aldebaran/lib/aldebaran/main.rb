require 'aldebaran/base'

module Aldebaran
  class Application < Base

    # we assume that the first file that requires 'aldebaran' is the
    # app_file. all other path related options are calculated based
    # on this path by default.
    set :app_file, caller_files.first || $0

    set :run, Proc.new { $0 == app_file }

    if run? && ARGV.any?
      require 'optparse'
      OptionParser.new { |op|
        op.on('-x')        {       set :lock, true }
        op.on('-e env')    { |val| set :environment, val.to_sym }
        op.on('-s server') { |val| set :server, val }
        op.on('-p port')   { |val| set :port, Integer(val) }
        op.on('-o addr')   { |val| set :bind, val }
      }.parse!(ARGV.dup)
    end
  end

  at_exit { Application.run! if $!.nil? && Application.run? }
end

include Aldebaran::Delegator

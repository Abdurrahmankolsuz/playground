require File.expand_path('../helper', __FILE__)

class AldebaranTest < Test::Unit::TestCase
  it 'creates a new Aldebaran::Base subclass on new' do
    app =
      Aldebaran.new do
        get '/' do
          'Hello World'
        end
      end
    assert_same Aldebaran::Base, app.superclass
  end
  
  it "responds to #template_cache" do
    assert_kind_of Tilt::Cache, Aldebaran::Base.new!.template_cache
  end
end

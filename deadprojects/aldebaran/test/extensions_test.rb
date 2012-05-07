require File.expand_path('../helper', __FILE__)

class ExtensionsTest < Test::Unit::TestCase
  module FooExtensions
    def foo
    end

    private
      def im_hiding_in_ur_foos
      end
  end

  module BarExtensions
    def bar
    end
  end

  module BazExtensions
    def baz
    end
  end

  module QuuxExtensions
    def quux
    end
  end

  module PainExtensions
    def foo=(name); end
    def bar?(name); end
    def fizz!(name); end
  end

  it 'will add the methods to the DSL for the class in which you register them and its subclasses' do
    Aldebaran::Base.register FooExtensions
    assert Aldebaran::Base.respond_to?(:foo)

    Aldebaran::Application.register BarExtensions
    assert Aldebaran::Application.respond_to?(:bar)
    assert Aldebaran::Application.respond_to?(:foo)
    assert !Aldebaran::Base.respond_to?(:bar)
  end

  it 'allows extending by passing a block' do
    Aldebaran::Base.register {
      def im_in_ur_anonymous_module; end
    }
    assert Aldebaran::Base.respond_to?(:im_in_ur_anonymous_module)
  end

  it 'will make sure any public methods added via Application#register are delegated to Aldebaran::Delegator' do
    Aldebaran::Application.register FooExtensions
    assert Aldebaran::Delegator.private_instance_methods.
      map { |m| m.to_sym }.include?(:foo)
    assert !Aldebaran::Delegator.private_instance_methods.
      map { |m| m.to_sym }.include?(:im_hiding_in_ur_foos)
  end

  it 'will handle special method names' do
    Aldebaran::Application.register PainExtensions
    assert Aldebaran::Delegator.private_instance_methods.
      map { |m| m.to_sym }.include?(:foo=)
    assert Aldebaran::Delegator.private_instance_methods.
      map { |m| m.to_sym }.include?(:bar?)
    assert Aldebaran::Delegator.private_instance_methods.
      map { |m| m.to_sym }.include?(:fizz!)
  end

  it 'will not delegate methods on Base#register' do
    Aldebaran::Base.register QuuxExtensions
    assert !Aldebaran::Delegator.private_instance_methods.include?("quux")
  end

  it 'will extend the Aldebaran::Application application by default' do
    Aldebaran.register BazExtensions
    assert !Aldebaran::Base.respond_to?(:baz)
    assert Aldebaran::Application.respond_to?(:baz)
  end

  module BizzleExtension
    def bizzle
      bizzle_option
    end

    def self.registered(base)
      fail "base should be BizzleApp" unless base == BizzleApp
      fail "base should have already extended BizzleExtension" unless base.respond_to?(:bizzle)
      base.set :bizzle_option, 'bizzle!'
    end
  end

  class BizzleApp < Aldebaran::Base
  end

  it 'sends .registered to the extension module after extending the class' do
    BizzleApp.register BizzleExtension
    assert_equal 'bizzle!', BizzleApp.bizzle_option
    assert_equal 'bizzle!', BizzleApp.bizzle
  end
end

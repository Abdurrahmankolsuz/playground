require File.expand_path('../helper', __FILE__)

class FooError < RuntimeError
end

class FooNotFound < Aldebaran::NotFound
end

class FooSpecialError < RuntimeError
  def code; 501 end
end

class MappedErrorTest < Test::Unit::TestCase
  def test_default
    assert true
  end

  describe 'Exception Mappings' do
    it 'invokes handlers registered with ::error when raised' do
      mock_app {
        set :raise_errors, false
        error(FooError) { 'Foo!' }
        get '/' do
          raise FooError
        end
      }
      get '/'
      assert_equal 500, status
      assert_equal 'Foo!', body
    end

    it 'passes the exception object to the error handler' do
      mock_app do
        set :raise_errors, false
        error(FooError) { |e| assert_equal(FooError, e.class) }
        get('/') { raise FooError }
      end
      get('/')
    end

    it 'uses the Exception handler if no matching handler found' do
      mock_app {
        set :raise_errors, false
        error(Exception) { 'Exception!' }
        get '/' do
          raise FooError
        end
      }

      get '/'
      assert_equal 500, status
      assert_equal 'Exception!', body
    end

    it 'walks down inheritance chain for errors' do
      mock_app {
        set :raise_errors, false
        error(RuntimeError) { 'Exception!' }
        get '/' do
          raise FooError
        end
      }

      get '/'
      assert_equal 500, status
      assert_equal 'Exception!', body
    end

    it 'favors subclass handler over superclass handler if available' do
      mock_app {
        set :raise_errors, false
        error(Exception) { 'Exception!' }
        error(FooError) { 'FooError!' }
        error(RuntimeError) { 'Exception!' }
        get '/' do
          raise FooError
        end
      }

      get '/'
      assert_equal 500, status
      assert_equal 'FooError!', body
    end

    it "sets env['aldebaran.error'] to the rescued exception" do
      mock_app {
        set :raise_errors, false
        error(FooError) {
          assert env.include?('aldebaran.error')
          assert env['aldebaran.error'].kind_of?(FooError)
          'looks good'
        }
        get '/' do
          raise FooError
        end
      }
      get '/'
      assert_equal 'looks good', body
    end

    it "raises errors from the app when raise_errors set and no handler defined" do
      mock_app {
        set :raise_errors, true
        get '/' do
          raise FooError
        end
      }
      assert_raise(FooError) { get '/' }
    end

    it "calls error handlers before raising errors even when raise_errors is set" do
      mock_app {
        set :raise_errors, true
        error(FooError) { "she's there." }
        get '/' do
          raise FooError
        end
      }
      assert_nothing_raised { get '/' }
      assert_equal 500, status
    end

    it "never raises aldebaran::NotFound beyond the application" do
      mock_app(Aldebaran::Application) { get('/') { raise Aldebaran::NotFound }}
      assert_nothing_raised { get '/' }
      assert_equal 404, status
    end

    it "cascades for subclasses of aldebaran::NotFound" do
      mock_app {
        set :raise_errors, true
        error(FooNotFound) { "foo! not found." }
        get '/' do
          raise FooNotFound
        end
      }
      assert_nothing_raised { get '/' }
      assert_equal 404, status
      assert_equal 'foo! not found.', body
    end

    it 'has a not_found method for backwards compatibility' do
      mock_app {
        not_found do
          "Lost, are we?"
        end
      }

      get '/test'
      assert_equal 404, status
      assert_equal "Lost, are we?", body
    end

    it 'inherits error mappings from base class' do
      base = Class.new(Aldebaran::Base)
      base.error(FooError) { 'base class' }

      mock_app(base) {
        set :raise_errors, false
        get '/' do
          raise FooError
        end
      }

      get '/'
      assert_equal 'base class', body
    end

    it 'overrides error mappings in base class' do
      base = Class.new(Aldebaran::Base)
      base.error(FooError) { 'base class' }

      mock_app(base) {
        set :raise_errors, false
        error(FooError) { 'subclass' }
        get '/' do
          raise FooError
        end
      }

      get '/'
      assert_equal 'subclass', body
    end

    it 'honors Exception#code if present' do
      mock_app do
        set :raise_errors, false
        error(501) { 'Foo!' }
        get('/') { raise FooSpecialError }
      end
      get '/'
      assert_equal 501, status
      assert_equal 'Foo!', body
    end
  end

  describe 'Custom Error Pages' do
    it 'allows numeric status code mappings to be registered with ::error' do
      mock_app {
        set :raise_errors, false
        error(500) { 'Foo!' }
        get '/' do
          [500, {}, 'Internal Foo Error']
        end
      }
      get '/'
      assert_equal 500, status
      assert_equal 'Foo!', body
    end

    it 'allows ranges of status code mappings to be registered with :error' do
      mock_app {
        set :raise_errors, false
        error(500..550) { "Error: #{response.status}" }
        get '/' do
          [507, {}, 'A very special error']
        end
      }
      get '/'
      assert_equal 507, status
      assert_equal 'Error: 507', body
    end

    it 'allows passing more than one range' do
      mock_app {
        set :raise_errors, false
        error(409..411, 503..509) { "Error: #{response.status}" }
        get '/' do
          [507, {}, 'A very special error']
        end
      }
      get '/'
      assert_equal 507, status
      assert_equal 'Error: 507', body
    end

    class FooError < RuntimeError
    end

    it 'runs after exception mappings and overwrites body' do
      mock_app {
        set :raise_errors, false
        error FooError do
          response.status = 502
          'from exception mapping'
        end
        error(500) { 'from 500 handler' }
        error(502) { 'from custom error page' }

        get '/' do
          raise FooError
        end
      }
      get '/'
      assert_equal 502, status
      assert_equal 'from custom error page', body
    end
  end
end

# encoding: UTF-8

require 'date'
require 'timeout'
require 'guard/guard'

# TODO:
#  * verify syntax before running
#  * better filtering of stack-trace output

class ClojureTestGuardRunner < ::Guard::Guard
  def log result
    `mkdir -p ./result_logs`
    date_str = DateTime.now.strftime '%y/%m/%d %H:%M:%S'
    date_str = date_str.tr('/:', '_')
    logfile  = "./result_logs/#{date_str}.log"
    File.open(logfile, 'w') {|f| f.write result}
  end

  def show msg
    puts(msg + "\n-------------------------------------\n\n")
  end
  
  def filter_message msg
    msg = msg[msg.index('ERROR in')..-1] if msg.index('ERROR in')
    msg = msg[0..msg.index("\n at")] if msg.index("\n at")
    msg = msg.gsub(/^lein test :only.*$/, "****")
    msg
  end
  
  def notify opts={}
    success = opts[:image] != :failed
    failure = opts[:image] == :failed
    message = opts[:message] || ''
    message = filter_message(message)
    log(message) if failure
    opts[:title] ||= 'Clojure Test'
    success ? show("success: #{message}") 
            : show(message)
    ::Guard::Notifier.notify(message, opts)
  end

  def failure opts={}
    notify opts.merge(image: :failed)
  end

  def success opts={}
    notify opts
  end

  def derive_test path
    path = path.gsub('/', '.').gsub(/\.clj$/, '')
    path += '_test' unless path.end_with? '_test'
    path
  end

  def test_exists test
    File.exists? "test/#{test.gsub('.', '/')}.clj"
  end

  def filter paths
    paths = paths.map {|p| derive_test(p)}
    paths = paths.select {|p| test_exists(p)}
  end

  def run_on_changes(paths)
    current_test = ''
    filter(paths).each do |path|
      current_test = path
      ::Guard.timeout(10) do
        test_result = `lein test #{path}`
        $?.success? ? success(message: path)
                    : failure(message: test_result)
      end
    end
  rescue Guard::TimeoutError
    failure(message: "Timeout: #{current_test}\n")
  end
end


module ::Guard
  class TimeoutError < Exception; end

  def self.timeout(n, &block)
    begin
      Timeout::timeout(n, &block)
    rescue Timeout::Error => e
      raise TimeoutError.new(e.message)
    end
  end

  class ClojureTestRunner < ClojureTestGuardRunner; end
end

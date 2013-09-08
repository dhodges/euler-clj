
guard 'ClojureTestRunner' do
  require './tools/clojure_test_runner'

  watch(%r[^src/(.+)\.clj$])       {|m| m[1]}
  watch(%r[^test/(.+_test)\.clj$]) {|m| m[1]}
end


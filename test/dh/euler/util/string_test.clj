(ns dh.euler.util.string_test
  (:require [dh.euler.util.string :refer :all]
            [clojure.test :refer :all]))

(deftest palindrome-test
  (is (= (palindrome? 1001)  true))
  (is (= (palindrome? 1000)  false))
  (is (= (palindrome? 52121) false))
  (is (= (palindrome? 52125) true)))

(deftest str-rotate-left-test
  (is (= (str-rotate-left "abc") "bca"))
  (is (= (str-rotate-left "abcdefghijk" 4) "efghijkabcd")))

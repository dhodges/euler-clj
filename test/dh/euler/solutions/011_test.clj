(ns dh.euler.solutions.011-test
  (:require [dh.euler.solutions.011 :refer :all]
            [clojure.test :refer :all]))

(deftest test-euler-011
  (is (= (euler-011) 70600674)))

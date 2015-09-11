(ns dh.euler.solutions.001-test
  (:use [clojure.test]
        [dh.euler.solutions.001]))

(deftest test-euler-001
    (is (= (euler-001) 233168)))

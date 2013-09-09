(ns dh.euler.solutions.problem_004_test
  (:require [dh.euler.solutions.problem_004 :refer :all]
            [clojure.test :refer :all]))


(deftest test-euler-004
  (is (= (euler-004) 906609)))

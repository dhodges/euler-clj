(ns dh.euler.solutions.problem_008_test
  (:require [dh.euler.solutions.problem_008 :refer :all]
            [clojure.test :refer :all]))


(deftest test-euler-008
  (is (= (euler-008) 40824)))

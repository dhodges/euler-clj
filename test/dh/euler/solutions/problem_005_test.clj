(ns dh.euler.solutions.problem_005_test
  (:require [dh.euler.solutions.problem_005 :refer :all]
            [clojure.test :refer :all]))


(deftest test-euler-005
  (is (= (euler-005) 232792560)))

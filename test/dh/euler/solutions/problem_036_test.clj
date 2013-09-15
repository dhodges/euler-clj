(ns dh.euler.solutions.problem_036_test
  (:use [dh.euler.solutions.problem_036 :refer [euler-036]]
        [clojure.test :refer :all]))

(deftest test-euler-036
  (is (= (euler-036) 872187)))

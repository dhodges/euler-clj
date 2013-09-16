(ns dh.euler.solutions.problem_024_test
  (:use [dh.euler.solutions.problem_024 :refer [euler-024]]
        [clojure.test :refer :all]))

(deftest test-euler-024
  (is (= (euler-024) "2783915460")))

(ns dh.euler.solutions.problem_028_test
  (:use [dh.euler.solutions.problem_028 :refer [euler-028]]
        [clojure.test :refer :all]))

(deftest test-euler-028
  (is (= (euler-028) 669171001)))

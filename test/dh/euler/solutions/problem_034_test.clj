(ns dh.euler.solutions.problem_034_test
  (:use [dh.euler.solutions.problem_034 :refer [euler-034]]
        [clojure.test :refer :all]))

(deftest test-euler-034
  (is (= (euler-034) 40730)))

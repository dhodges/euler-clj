(ns dh.euler.solutions.problem_029_test
  (:use [dh.euler.solutions.problem_029 :refer [euler-029]]
        [clojure.test :refer :all]))

(deftest test-euler-029
  (is (= (euler-029) 9183)))

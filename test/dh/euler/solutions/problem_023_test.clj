(ns dh.euler.solutions.problem_023_test
  (:use [dh.euler.solutions.problem_023 :refer [euler-023]]
        [clojure.test :refer :all]))

(deftest test-euler-023
  (is (= (euler-023) 4179871)))

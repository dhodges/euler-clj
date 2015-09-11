(ns dh.euler.solutions.problem_075_test
  (:use [dh.euler.problems.problem_075 :refer [euler-075 single-triangle? multiple-of-any]]
        [clojure.test :refer :all]))


(deftest test-single-triangle
  (and (= (single-triangle? 182) true)
       (= (single-triangle? 120) false)))

(deftest test-multiple-of-any
  (and (= (multiple-of-any 10 [3 7]) false)
       (= (multiple-of-any 10 [3 7 5]) true)))

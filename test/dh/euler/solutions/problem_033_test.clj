(ns dh.euler.solutions.problem_033_test
  (:use [dh.euler.solutions.problem_033 :refer [euler-033]]
        [clojure.test :refer :all]))

(deftest test-euler-033
  (is (= (euler-033) 100)))

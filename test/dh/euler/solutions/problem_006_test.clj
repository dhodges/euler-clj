(ns dh.euler.solutions.problem_006_test
  (:require [dh.euler.solutions.problem_006 :refer :all]
            [clojure.test :refer :all]))


(deftest test-euler-006
  (is (= (euler-006) 25164150)))

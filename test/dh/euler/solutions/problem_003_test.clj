(ns dh.euler.solutions.problem_003_test
  (:require [dh.euler.solutions.problem_003 :refer :all]
            [clojure.test :refer :all]))


(deftest test-euler-003
  (is (= (euler-003) 6857)))

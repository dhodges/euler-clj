(ns dh.euler.solved.problem_001_test
  (:require [dh.euler.solved.problem_001 :refer :all]
            [clojure.test :refer :all]))


(deftest test-euler-001
    (is (= (euler-001) 233168)))

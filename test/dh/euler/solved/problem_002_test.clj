(ns dh.euler.solved.problem_002_test
  (:require [dh.euler.solved.problem_002 :refer :all]
            [clojure.test :refer :all]))


(deftest test-euler-002
  (is (= (euler-002) 4613732)))

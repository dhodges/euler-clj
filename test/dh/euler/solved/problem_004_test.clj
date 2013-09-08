(ns dh.euler.solved.problem_004_test
  (:require [dh.euler.solved.problem_004 :refer :all]
            [clojure.test :refer :all]))


(deftest test-euler-004
  (is (= (euler-004) 906609)))

(ns dh.euler.solved.problem_003_test
  (:require [dh.euler.solved.problem_003 :refer :all]
            [clojure.test :refer :all]))


(deftest test-euler-003
  (is (= (euler-003) 6857)))

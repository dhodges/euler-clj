(ns dh.euler.solutions.problem_022_test
  (:use [dh.euler.solutions.problem_022 :refer [euler-022]]
        [clojure.test :refer :all]))

(deftest test-euler-022
  (is (= (euler-022) 871198282)))

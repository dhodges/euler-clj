(ns dh.euler.solutions.problem_030_test
  (:use [dh.euler.solutions.problem_030 :refer [euler-030]]
        [clojure.test :refer :all]))

(deftest test-euler-030
  (is (= (euler-030) 443839)))

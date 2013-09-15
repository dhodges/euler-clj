(ns dh.euler.solutions.problem_032_test
  (:use [dh.euler.solutions.problem_032 :refer [euler-032]]
        [clojure.test :refer :all]))

(deftest test-euler-032
  (is (= (euler-032) 45228)))

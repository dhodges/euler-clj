(ns dh.euler.solutions.problem_025_test
  (:use [dh.euler.solutions.problem_025 :refer [euler-025]]
        [clojure.test :refer :all]))

(deftest test-euler-025
  (is (= (euler-025) 4782)))

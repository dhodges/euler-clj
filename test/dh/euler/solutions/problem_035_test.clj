(ns dh.euler.solutions.problem_035_test
  (:use [dh.euler.solutions.problem_035 :refer [euler-035]]
        [clojure.test :refer :all]))

(deftest test-euler-035
  (is (= (euler-035) 55)))

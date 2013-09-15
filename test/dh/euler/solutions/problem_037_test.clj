(ns dh.euler.solutions.problem_037_test
  (:use [dh.euler.solutions.problem_037 :refer [euler-037]]
        [clojure.test :refer :all]))

(deftest test-euler-037
  (is (= (euler-037) 748317)))

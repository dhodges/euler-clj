(ns dh.euler.solutions.problem_026_test
  (:use [dh.euler.solutions.problem_026 :refer [euler-026]]
        [clojure.test :refer :all]))

(deftest test-euler-026
  (is (= (euler-026) 983)))

(ns dh.euler.solutions.problem_038_test
  (:use [dh.euler.solutions.problem_038 :refer [euler-038]]
        [clojure.test :refer :all]))

(deftest test-euler-038
  (is (= (euler-038) 932718654)))

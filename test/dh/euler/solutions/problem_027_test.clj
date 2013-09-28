(ns dh.euler.solutions.problem_027_test
  (:use [dh.euler.solutions.problem_027 :refer [euler-027]]
        [clojure.test :refer :all]))

(deftest test-euler-027
  (is (= (euler-027) -59231)))

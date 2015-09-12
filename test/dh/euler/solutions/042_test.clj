(ns dh.euler.solutions.042_test
  (:use [dh.euler.solutions.042 :refer [euler-042]]
        [clojure.test]))

(deftest test-euler-042
  (is (= (euler-042) 162)))

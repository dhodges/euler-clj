(ns dh.euler.solutions.035-test
  (:use [dh.euler.solutions.035 :refer [euler-035]]
        [clojure.test]))

(deftest test-euler-035
  (is (= (euler-035) 55)))

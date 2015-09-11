(ns dh.euler.solutions.036-test
  (:use [dh.euler.solutions.036 :refer [euler-036]]
        [clojure.test]))

(deftest test-euler-036
  (is (= (euler-036) 872187)))

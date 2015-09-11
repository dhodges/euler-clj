(ns dh.euler.solutions.024-test
  (:use [dh.euler.solutions.024 :refer [euler-024]]
        [clojure.test]))

(deftest test-euler-024
  (is (= (euler-024) "2783915460")))

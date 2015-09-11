(ns dh.euler.solutions.023-test
  (:use [dh.euler.solutions.023 :refer [euler-023]]
        [clojure.test]))

(deftest test-euler-023
  (is (= (euler-023) 4179871)))

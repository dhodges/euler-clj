(ns dh.euler.solutions.025-test
  (:use [dh.euler.solutions.025 :refer [euler-025]]
        [clojure.test]))

(deftest test-euler-025
  (is (= (euler-025) 4782)))

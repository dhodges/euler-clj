(ns dh.euler.solutions.037-test
  (:use [dh.euler.solutions.037 :refer [euler-037]]
        [clojure.test]))

(deftest test-euler-037
  (is (= (euler-037) 748317)))

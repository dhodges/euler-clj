(ns dh.euler.solutions.033-test
  (:use [dh.euler.solutions.033 :refer [euler-033]]
        [clojure.test :refer :all]))

(deftest test-euler-033
  (is (= (euler-033) 100)))

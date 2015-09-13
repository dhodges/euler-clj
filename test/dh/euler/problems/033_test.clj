(ns dh.euler.problems.033-test
  (:use [dh.euler.problems.033 :refer [euler-033]]
        [clojure.test :refer :all]))

(deftest test-euler-033
  (is (= (euler-033) 100)))

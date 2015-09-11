(ns dh.euler.solutions.026-test
  (:use [dh.euler.solutions.026 :refer [euler-026]]
        [clojure.test :refer :all]))

(deftest test-euler-026
  (is (= (euler-026) 983)))

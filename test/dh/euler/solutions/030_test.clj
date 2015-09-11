(ns dh.euler.solutions.030-test
  (:use [dh.euler.solutions.030 :refer [euler-030]]
        [clojure.test :refer :all]))

(deftest test-euler-030
  (is (= (euler-030) 443839)))

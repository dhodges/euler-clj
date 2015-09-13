(ns dh.euler.problems.032-test
  (:use [dh.euler.problems.032 :refer [euler-032]]
        [clojure.test :refer :all]))

(deftest test-euler-032
  (is (= (euler-032) 45228)))

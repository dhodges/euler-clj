(ns dh.euler.problems.034-test
  (:use [dh.euler.problems.034 :refer [euler-034]]
        [clojure.test :refer :all]))

(deftest test-euler-034
  (is (= (euler-034) 40730)))

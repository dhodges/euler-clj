(ns dh.euler.solutions.034-test
  (:use [dh.euler.solutions.034 :refer [euler-034]]
        [clojure.test :refer :all]))

(deftest test-euler-034
  (is (= (euler-034) 40730)))

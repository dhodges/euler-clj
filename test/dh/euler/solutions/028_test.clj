(ns dh.euler.solutions.028-test
  (:use [dh.euler.solutions.028 :refer [euler-028]]
        [clojure.test :refer :all]))

(deftest test-euler-028
  (is (= (euler-028) 669171001)))

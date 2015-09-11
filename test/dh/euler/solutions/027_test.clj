(ns dh.euler.solutions.027-test
  (:use [dh.euler.solutions.027 :refer [euler-027]]
        [clojure.test :refer :all]))

(deftest test-euler-027
  (is (= (euler-027) -59231)))

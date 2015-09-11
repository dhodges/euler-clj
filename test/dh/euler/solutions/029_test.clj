(ns dh.euler.solutions.029-test
  (:use [dh.euler.solutions.029 :refer [euler-029]]
        [clojure.test :refer :all]))

(deftest test-euler-029
  (is (= (euler-029) 9183)))

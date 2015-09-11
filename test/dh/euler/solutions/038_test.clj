(ns dh.euler.solutions.038-test
  (:use [dh.euler.solutions.038 :refer [euler-038]]
        [clojure.test :refer :all]))

(deftest test-euler-038
  (is (= (euler-038) 932718654)))

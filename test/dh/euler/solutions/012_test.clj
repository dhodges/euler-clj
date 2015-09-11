(ns dh.euler.solutions.012-test
  (:use [dh.euler.solutions.012]
            [clojure.test]))

(deftest test-euler-012
  (is (= (euler-012) 76576500)))

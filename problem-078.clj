#^:shebang '[
             exec clj clojure.lang.Script "$0" -- "$@"
             ]

(ns dh.euler
  (:use [project_euler.dh_utils])
  (:use [clojure.contrib.test-is]))

;; Let p(n) represent the number of different ways in which n coins can be separated
;; into piles.
;;
;; For example, five coins can be separated into piles in exactly seven different ways,
;; so p(5) = 7.
;;
;; Find the least value of n for which p(n) is divisible by one million.


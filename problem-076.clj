#^:shebang '[
             exec clj clojure.lang.Script "$0" -- "$@"
             ]

(ns dh.euler
  (:use [project_euler.dh_utils])
  (:use [clojure.contrib.test-is]))

;; It is possible to srite five as a sum in exactly six different ways:
;;
;;         4 + 1
;;         3 + 2
;;         3 + 1 + 1
;;         2 + 2 + 1
;;         2 + 1 + 1 + 1
;;         1 + 1 + 1 + 1 + 1
;;
;; How many different ways can one hundred be written as a sum of at least
;; two positive integers?


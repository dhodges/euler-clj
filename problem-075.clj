#^:shebang '[
             exec clj clojure.lang.Script "$0" -- "$@"
             ]

(ns dh.euler
  (:use [project_euler.dh_utils])
  (:use [clojure.contrib.test-is]))

;; It turns out that 12cm is the smallest length of wire that can be bent to form an
;; integer sided right angle triangle in exactly one way, but there are many more examples.
;;
;;             12cm: (3, 4, 5)
;;             24cm: (6, 8, 10)
;;             30cm: (5, 12, 13)
;;             36cm: (9, 12, 15)
;;             40cm: (8, 15, 17)
;;             48cm: (12, 16, 20)
;;
;; In contrast, some lengths of wire, like 20cm, cannot be bent to form an integer sided
;; right angle triangle, and other lengths allow more than one solution to be found -
;; for example, using 120cm it is possible to form exactly three different
;; integer sided right angle triangles.
;;
;;             120cm: (30, 40, 50), (20, 48, 52), (24, 45, 51)
;;
;; Given that L is the length of the wire, for how many values of L ≤ 2,000,000
;; can exactly one integer sided right angle triangle be formed?



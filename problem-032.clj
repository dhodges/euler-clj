#^:shebang '[
             exec clj clojure.lang.Script "$0" -- "$@"
             ]

(ns dh.euler)

;; http://projecteuler.net/index.php?section=problems&id=32
;;
;; Problem 32
;; 06 December 2002
;;
;; We shall say that an n-digit number is pandigital if it makes use of all the digits
;; 1 to n exactly once; for example, the 5-digit number, 15234, is 1 through 5 pandigitial.
;;
;; The product 7254 is unusual, as the identity, 39 � 186 = 7254,
;; containing multiplicand, multiplier, and product is 1 through 9 pandigital.
;;
;; Find the sum of all products whose multiplicand/multiplier/product
;; identity can be written as a 1 through 9 pandigital.
;;
;; HINT: Some products can be obtained in more than one way so be sure
;; to only include it once in your sum.




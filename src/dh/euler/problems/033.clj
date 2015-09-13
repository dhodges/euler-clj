;; The fraction 49/98 is a curious fraction, as an inexperienced mathematician
;; in attempting to simplify it may incorrectly believe that 49/98 = 4/8,
;; which is correct, is obtained by cancelling the 9s.
;;
;; We shall consider fractions like, 30/50 = 3/5, to be trivial examples.
;;
;; There are exactly four non-trivial examples of this type of fraction,
;; less than one in value, and containing two digits in the
;; numerator and denominator.
;;
;; If the product of these four fractions is given in its lowest common terms,
;; find the value of the denominator.
;;
;; http://projecteuler.net/problem=33

(ns dh.euler.problems.033
  (:use [dh.euler.util.core :refer [gcd]]))


;; Notes

;; (1) n < d
;;
;; (a)   10x + n      n                (b)   10n + x      n
;;       -------   =  -                      -------   =  -
;;       10d + x      d                      10x + d      d


;; (c)   10x + n      n                (d)   10n + x      n
;;       -------   =  -                      -------   =  -
;;       10x + d      d                      10d + x      d


;; solving for (a)                     solving for (b)

;;  10x  + n   = (10dn + xn) / d       10n  + x  = (10xn + dn) / d
;;  10dx + dn  =  10dn + xn            10nd + xd =  10xn + dn
;;  10dx       =   9dn + xn             9dn      =  10xn - xd
;; x(10d - n)  =   9dn                  9dn      = x(10n - d)


;; solving for (c)                      solving for (d)

;; 10x  + n  = (10xn + dn) / d          10n  + x   = (10dn + nx) / d
;; 10dx + dn =  10xn + dn               10dn + dx  =  10dn + nx
;; 10dx      =  10xn                           dx  =  nx
;;      d    =  n [impossible, from (1)]       d   =  n [impossible, from (1)]


;; 9dn = x(10d - n)   ; from (a)
;; 9dn = x(10n - d)   ; from (b)


(defn euler-033
  []
  (time (let [f (fn [[x d n]]
                  (or (= (* 9 d n)
                         (* x (- (* 10 d) n)))
                      (= (* 9 d n)
                         (* x (- (* 10 n) d)))))

              xdns (filter f (for [x (range 1 10)
                                   d (range 1 11)
                                   n (range 1  d)]
                               [x d n]))

              ns (map (fn [[x d n]] n) xdns)
              ds (map (fn [[x d n]] d) xdns)

              n (reduce * ns)
              d (reduce * ds)]

          (assert (= 4 (count ns)))
          (assert (= 4 (count ds)))

          (/ d (gcd n d)))))

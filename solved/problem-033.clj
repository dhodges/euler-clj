#^:shebang '[
             exec clj clojure.lang.Script "$0" -- "$@"
             ]

(ns dh.euler
  (:use [clojure.contrib.test-is]))

;; http://projecteuler.net/index.php?section=problems&id=33
;;
;; Problem 33
;; 20 December 2002
;;
;; The fraction 49/98 is a curious fraction, as an inexperienced mathematician
;; in attempting to simplify it may incorrectly believe that 49/98 = 4/8,
;; which is correct, is obtained by cancelling the 9s.
;;
;; We shall consider fractions like, 30/50 = 3/5, to be trivial examples.
;;
;; There are exactly four non-trivial examples of this type of fraction,
;; less than one in value, and containing two digits in the numerator and denominator.
;;
;; If the product of these four fractions is given in its lowest common terms,
;; find the value of the denominator.


(defn common-chars
  [s1 s2]
  (let [common (ref [])]
    (dosync
     (doseq [c s1]
       (if (.contains s2 (str c))
         (ref-set common (conj @common c))))
     (apply str (distinct (sort @common))))))


(defn chars-in-common?
  [s1 s2]
  (< 0 (count (common-chars s1 s2))))


(defn str-remove
  "returns a copy of s without any char from rem-str"
  [rem-str s]
  (let [result (ref [])]
    (dosync
     (doseq [c s]
       (let [c (str c)]
         (if (not (.contains rem-str c))
           (ref-set result (conj @result c))))))
    (apply str @result)))


(defn curious-fraction?
  [n d]
  (if (or (= d 0)
          (not (< n d)))
    false
    (let [initial-result (/ n d)]
      (let [n (str n)
            d (str d)]
        (if (or (= d "0")
                (not (chars-in-common? n d))
                (and (.endsWith n "0")
                     (.endsWith d "0")))
          false
          (let [common (common-chars n d)
                n      (str-remove common n)
                d      (str-remove common d)]
            (if (or (= n "")
                    (= d "")
                    (= d "0"))
              false        
              (= initial-result
                 (/ (Integer/valueOf n)
                    (Integer/valueOf d))))))))))


(defn find-curious-fractions
  []
  (for [n  (range 10)
        n1 (range 10)
        d  (range 10)
        d1 (range 10)
        :when (curious-fraction? (Integer/valueOf (str n n1))
                                 (Integer/valueOf (str d d1)))]
    [(Integer/valueOf (str n n1))
     (Integer/valueOf (str d d1))]))



(defn euler-033
  []
  (time
   (let [result (reduce * (map #(/ (first %) (second %))
                               (find-curious-fractions)))
         [n d] (.split (str result) "/")]
     (Integer/valueOf d))))

(deftest test-euler-033
  []
  (is (= 100 (solution))))

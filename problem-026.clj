#^:shebang '[
             exec clj clojure.lang.Script "$0" -- "$@"
             ]

(ns user
  (:use [project_euler.dh_utils])
  (:use [clojure.contrib.test-is]))

;; http://projecteuler.net/index.php?section=problems&id=26
;;
;; Problem 26
;; 13 September 2002
;;
;; A unit fraction contains 1 in the numerator. The decimal representation of the unit fractions 
;; with denominators 2 to 10 are given:
;;
;;     1/2	= 	0.5
;;     1/3	= 	0.(3)
;;     1/4	= 	0.25
;;     1/5	= 	0.2
;;     1/6	= 	0.1(6)
;;     1/7	= 	0.(142857)
;;     1/8	= 	0.125
;;     1/9	= 	0.(1)
;;     1/10	= 	0.1
;;
;; Where 0.1(6) means 0.166666..., and has a 1-digit recurring cycle. It can be seen that 
;; 1/7 has a 6-digit recurring cycle.
;;
;; Find the value of d < 1000 for which 1/d contains the longest recurring cycle 
;; in its decimal fraction part.


; Notes:
; apparently the length of the recurring cycle (if any)
; will not be larger than the denominator
; e.g: the size of the recurring cycle in 1/7 (142857) is 6
; and 6 < 7

; Console.printf

(defn find-recurring-cycle
  [n]
  (let [cycle (ref "")
        s     (.substring (str (/ 1.0 n)) 2)]
    (dosync
     (doseq [ndx (range (dec (count s)))]
       (let [s (.substring s ndx)
             c (str (first s))
             cndx (.indexOf (.substring s 1) c)]
         (when (> cndx -1)
           (let [cndx (inc cndx)
                 astr (.substring s 0 cndx)
                 bstr (.substring s cndx)]
             (if (and (.startsWith bstr astr)
                      (> (count astr) (count @cycle)))
               (ref-set cycle astr)))))))
    @cycle))


(defn print-cycle
  [num cycle]
  (let [spaces "                      "
        ndx    (.indexOf (str (/ 1.0 num)) cycle)
        indent (.substring spaces 0 ndx)]
    (println (str "1 / " num " = "  (/ 1.0 num)))
    (println (str "      " indent " -> " cycle " <-"))))


(defn euler-26
  []
  (time
   (loop [n      2
          num   -1
          cycle ""]
     (if (>= n 1000)
       [num cycle]
       (let [pat  (find-recurring-cycle n)
             num  (if (> (count pat) (count cycle))
                    n
                    num)
             cycle (if (> (count pat) (count cycle))
                     pat
                     cycle)]
         (recur (inc n)
                num
                cycle))))))
                
                
(defn find-cycles
  []
  (time
   (loop [n 2
          cycles []]
     (if (>= n 1000)
       cycles
       (let [cycle (find-recurring-cycle n)]
         (recur (inc n)
                (if (> (count cycle) 1)
                  (conj cycles [n cycle])
                  cycles)))))))

(defn print-cycles
  []
  (let [cycles (find-cycles-new)
        comp   (proxy [java.util.Comparator] []
                 (compare [o1 o2]
                          (let [len1 (count (second o1))
                                len2 (count (second o2))]
                            (cond (< len1 len2)
                                  -1
                                  (= len1 len2)
                                  0
                                  :else
                                  1))))]
    (doseq [c (take 10 (reverse (sort comp cycles)))]
      (printf "%s\n" c))))


(deftest test-euler-26
  (is (not (member-of-sequence? (euler-26)
                               [405
                                810]))))

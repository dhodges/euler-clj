(ns dh.euler.util.sequences)

; pascal's triangle
;
; http://oeis.org/A007318

; 1
; 1  1
; 1  2  1
; 1  3  3  1
; 1  4  6  4  1
;
; "Can be viewed as an array, read by antidiagonals,
;  where the entries in the first row and column are all 1's
;  and A(i,j) = A(i-1,j) + A(i,j-1) for all other entries."
;  - Gerald McGarvey, Aug 17 2004

(def pascal-diagonal-item
  (memoize
   (fn pdi [row col]
     (cond
      (<= row 0) 0
      (<= col 0) 0
      (=  row 1) 1
      :else
      (+ (pascal-diagonal-item (dec row) col)
         (pascal-diagonal-item row (dec col)))))))

(defn pascal-diagonal
  [row nitems]
  (loop [line [1]
         ndx  1]
    (if (= ndx nitems)
      line
      (recur (conj line (pascal-diagonal-item row (inc ndx)))
             (inc ndx)))))

; each row item is determined by:
; C(n, k+1) = C(n, k) * (n-k) / (k+1)

(defn pascal-row
  [n]
  (loop [line [1]
         ndx  0]
    (if (= ndx n)
      line
      (recur (conj line
                   (/ (* (nth line ndx)
                         (- n ndx))
                      (inc ndx)))
             (inc ndx)))))

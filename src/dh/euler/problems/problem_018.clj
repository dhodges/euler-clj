;; By starting at the top of the triangle below and moving to adjacent numbers
;; on the row below, the maximum total from top to bottom is 23.
;;
;;       *3*
;;     *7*  5
;;    2  *4*  6
;;  8   5  *9* 3
;;
;; That is, 3 + 7 + 4 + 9 = 23.
;;
;; Find the maximum total from top to bottom of the triangle below:
;;
;;                             75
;;                           95  64
;;                         17  47  82
;;                       18  35  87  10
;;                     20  04  82  47  65
;;                   19  01  23  75  03  34
;;                 88  02  77  73  07  63  67
;;               99  65  04  28  06  16  70  92
;;             41  41  26  56  83  40  80  70  33
;;           41  48  72  33  47  32  37  16  94  29
;;         53  71  44  65  25  43  91  52  97  51  14
;;       70  11  33  28  77  73  17  78  39  68  17  57
;;     91  71  52  38  17  14  91  43  58  50  27  29  48
;;   63  66  04  68  89  53  67  30  73  16  69  87  40  31
;; 04  62  98  27  23  09  70  98  73  93  38  53  60  04  23
;;
;; NOTE: As there are only 16384 routes, it is possible to solve this problem
;; by trying every route. However, Problem 67 is the same challenge with
;; a triangle containing one-hundred rows; it cannot be solved by brute force,
;; and requires a clever method! ;o)
;;
;; http://projecteuler.net/problem=18


(ns dh.euler.problems.problem_018)

(def text "                 75
                          95  64
                        17  47  82
                      18  35  87  10
                    20  04  82  47  65
                  19  01  23  75  03  34
                88  02  77  73  07  63  67
              99  65  04  28  06  16  70  92
            41  41  26  56  83  40  80  70  33
          41  48  72  33  47  32  37  16  94  29
        53  71  44  65  25  43  91  52  97  51  14
      70  11  33  28  77  73  17  78  39  68  17  57
    91  71  52  38  17  14  91  43  58  50  27  29  48
  63  66  04  68  89  53  67  30  73  16  69  87  40  31
04  62  98  27  23  09  70  98  73  93  38  53  60  04  23 ")

(def rows (for [line (.split text "\\n")]
            (map #(Integer/parseInt %)
                 (filter #(> (count %) 0)
                         (seq (.split (.trim line) " "))))))

(def rowcount (count rows))
(def maxrow   (dec rowcount))

(defn value-at
  [row col]
  (if (or (< row 0)
          (> row maxrow)
          (< col 0)
          (>= col (count (nth rows row))))
    0
    (nth (nth rows row) col)))


;; From the cells available
;; (left or right in the row below)
;; always choose the one with the highest number.

;; (defn top-down
;;   []
;;   (loop [row  0
;;          col  0
;;          sum (value-at row col)]
;;     (println (format "%s" [row col]))
;;     (if (>= row maxrow)
;;       sum
;;       (let [row (inc row)
;;             a   (value-at row col)
;;             b   (value-at row (inc col))
;;             col (if (>= a b) col (inc col))]
;;         (recur row
;;                col
;;                (+ sum (max a b)))))))

;; Work upwards from the bottom row

(defn best-choice-above
  [row col]
  (let [row (dec row)
        a   (value-at row (dec col))
        b   (value-at row col)
        col (if (> a b) (dec col) col)]
    [row col]))


(defn bottom-up
  ([row col] (bottom-up row col '()))
  ([row col values]
     (if (< row 0)
       values
       (let [value  (value-at row col)
             values (cons value values)
             [row col] (best-choice-above row col)]
         (recur row col values)))))

(defn sum-bottom-up
  [row col]
  (apply + (bottom-up row col)))

(defn bottom-sums
  []
  (let [row 14]
    (for [col (range (count (nth rows maxrow)))]
      (sum-bottom-up row col))))


(defn euler-018
  []
  (apply max (bottom-sums)))

;; (deftest test-euler-018
;;   (= (not (member-of-sequence? (euler-018) [849
;;                                             1064
;;                                             1068]))))

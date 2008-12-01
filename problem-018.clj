#^:shebang '[
             exec clj clojure.lang.Script "$0" -- "$@"
             ]

(ns user)

;; Problem 18
;; 31 May 2002

;; By starting at the top of the triangle below and moving to adjacent numbers on the row below, the maximum total from top to bottom is 23.

;; *3*
;; *7*  5
;;  2  *4*  6
;;  8   5  *9* 3

;; That is, 3 + 7 + 4 + 9 = 23.

;; Find the maximum total from top to bottom of the triangle below:

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

;; NOTE: As there are only 16384 routes, it is possible to solve this problem by trying every route. However, Problem 67, is the same challenge with a triangle containing one-hundred rows; it cannot be solved by brute force, and requires a clever method! ;o)

(defstruct node :val :left :right)

(def number-rows '((75)
                   (95  64)
                   (17  47  82)
                   (18  35  87  10)
                   (20   4  82  47  65)
                   (19   1  23  75   3  34)
                   (88   2  77  73   7  63  67)
                   (99  65   4  28   6  16  70  92)
                   (41  41  26  56  83  40  80  70  33)
                   (41  48  72  33  47  32  37  16  94  29)
                   (53  71  44  65  25  43  91  52  97  51  14)
                   (70  11  33  28  77  73  17  78  39  68  17  57)
                   (91  71  52  38  17  14  91  43  58  50  27  29  48)
                   (63  66   4  68  89  53  67  30  73  16  69  87  40  31)
                   ( 4  62  98  27  23   9  70  98  73  93  38  53  60   4  23)))

(defn make-tree
  "returns the root of a tree built up from rows of numbers"
  [rows]
  (let [nodes (ref (map #(struct node % nil nil) (last rows)))]
    (loop [rows (rest (reverse rows))]
      (if (empty? rows)
        (last @nodes)
        (let [row (first rows)
              last-nodes (reverse (take (inc (count row)) (reverse @nodes)))]
          (dosync 
           (ref-set nodes 
                    (concat @nodes 
                          (for [n (range (count row))]
                            (struct node (nth row n) 
                                    (nth last-nodes n) 
                                    (nth last-nodes (inc n)))))))
          (recur (rest rows)))))))
        
(defn find-max-total
  [rows]
  (loop [totals (first rows)
         rows   (rest rows)]
    (println totals)
    (if (empty? rows)
      (reduce max totals)
      (let [row (first rows)
            new-totals (ref '())]
        (dosync         
         (doseq [n (range (count totals))]
           (ref-set new-totals 
                    (concat @new-totals
                            [(+ (nth totals n) (nth row n)) 
                             (+ (nth totals n) (nth row (inc n)))]))))
        (recur @new-totals (rest rows))))))

    

     
(find-max-total number-rows)

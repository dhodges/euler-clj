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

(def number-rows           '((75)
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

        
(defn take-last
  [n coll]
  (reverse (take n (reverse coll))))



(defn find-max-total
  [rows]
  (loop [totals (first rows)
         rows   (rest rows)]
    (if (empty? rows)
      (reduce max totals)
      (let [row (first rows)
            last-row (take-last (dec (count row)) totals)
            new-totals (ref '())]
        (dosync         
         (doseq [n (range (count last-row))]
           (ref-set new-totals 
                    (concat @new-totals
                            [(+ (nth last-row n) (nth row n)) 
                             (+ (nth last-row n) (nth row (inc n)))]))))
        (recur @new-totals (rest rows))))))

         
;(find-max-total number-rows)

; != 849
; != 1064

; Something to try:
;
; Starting from the bottom row choose a subset, e.g. the top five numbers,
; and for each number chosen work back to the top of the tree,
; choosing the max of two choices with each step.
; e.g:
; 75
; 95
; 47
; 87
; 82
; 75
; 73
; 28
; 83
; 47
; 43
; 73
; 91
; 67
; 98

; Something else to try:
;
; Scan each line from top to bottom.
; Note the top 5 numbers on each line.
; For each saved line of 5 numbers, try to connect as many of them as possible.
; This will build a considerably smaller subtree, 
; which can then be searched by brute force.

(defstruct nub :value :row-ndx :nub-ndx)

(def nub-comp (proxy [java.util.Comparator] []
                (compare [o1 o2] 
                         (let [v1 (o1 :value) v2 (o2 :value)]
                           (cond (< v1 v2) -1
                                 (= v1 v2)  0
                                 :else      1)))))
(defn max-in-each-row
  [n rows]
  (for [row-ndx (range (count rows))]
    (let [r (nth rows row-ndx)]
      (take n 
            (reverse 
             (sort nub-comp 
                   (for [nub-ndx (range (count r))]
                     (struct nub (nth r nub-ndx) row-ndx nub-ndx))))))))
         
        
(defn nodes-adjacent-to-ndx
  [ndx row]
  (let [ndx1 ndx 
        ndx2 (inc ndx)]
    (for [node row :when (or (= (node :nub-ndx) ndx1)
                             (= (node :nub-ndx) ndx2))]
      node)))
      
                 
(defn make-new-tree
  [rows]
  (let [tree (ref [(first rows)])
        rows (rest rows)]
    (dosync
     (doseq [row-ndx (range (count rows))]
       (let [r  (last @tree)
             r1 (nth rows row-ndx)
             new-tree (reverse (rest (reverse @tree)))]
         (println (format "   r:  %s" r))
         (println (format "  r1:  %s" r1))
         (println (format "tree:  %s" @tree))
         (ref-set tree
                  (conj new-tree 
                        (for [nub r :when (nodes-adjacent-to-ndx (nub :nub-ndx) r1)]
                          nub))))))
    @tree))

           
           
; try making a new, stripped subtree from the full tree
; instead of reading line by line and saving he largest 5 numbers,
; just traverse the nodes, saving only the large ones

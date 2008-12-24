#^:shebang '[
             exec clj clojure.lang.Script "$0" -- "$@"
             ]

(ns user
  (:use [project_euler.dh_utils])
  (:use [clojure.contrib.test-is]))

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



;; Currently attempt at a solution is to navigate down from the outer extremities,
;; always choosing the maximum of two choices available from the next row.
;;
;;                              ↓
;;                           ↓ 75 ↓
;;                         ↓ 95  64 ↓
;;                       ↓ 17  47  82 ↓
;;                     ↓ 18  35  87  10 ↓
;;                   ↓ 20  04  82  47  65 ↓
;;                 ↓ 19  01  23  75  03  34 ↓
;;               ↓ 88  02  77  73  07  63  67 ↓
;;             ↓ 99  65  04  28  06  16  70  92 ↓
;;           ↓ 41  41  26  56  83  40  80  70  33 ↓
;;         ↓ 41  48  72  33  47  32  37  16  94  29 ↓
;;       ↓ 53  71  44  65  25  43  91  52  97  51  14 ↓
;;     ↓ 70  11  33  28  77  73  17  78  39  68  17  57 ↓
;;   ↓ 91  71  52  38  17  14  91  43  58  50  27  29  48 ↓
;; ↓ 63  66  04  68  89  53  67  30  73  16  69  87  40  31 ↓
;; 04  62  98  27  23  09  70  98  73  93  38  53  60  04  23


(defn max-path-from
  [rows row-ndx nub-ndx]
  (let [row (nth rows row-ndx)
        val (nth row  nub-ndx)]
    (if (= row-ndx (dec (count rows)))
      (list val)
      (cons val
            (let [next (nth rows (inc row-ndx))
                  a (nth next nub-ndx)
                  b (nth next (inc nub-ndx))]
              (if (>= a b)
                (path-from rows (inc row-ndx) nub-ndx)
                (path-from rows (inc row-ndx) (inc nub-ndx))))))))

(defn max-paths
  [rows]

  (concat
   
   (for [row-ndx (range 1 (count rows))]
     (concat
      (for [row-ndx2 (range 0 row-ndx 1)]
        (first (nth rows row-ndx2)))
      (max-path-from rows row-ndx 0)))
  
   (for [row-ndx (range 1 (count rows))]
     (concat
      (for [row-ndx2 (range 0 row-ndx 1)]
        (last (nth rows row-ndx2)))
      (max-path-from rows row-ndx (dec (count (nth rows row-ndx))))))
  
   (list (max-path-from rows 0 0))))


(defn euler-018
  []
  (time
   (reduce max (for [row (max-paths rows)] (apply + row)))))

(deftest test-euler-018
  (= (not (member-of-sequence? (euler-018) [849
                                            1064
                                            1068]))))


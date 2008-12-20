#^:shebang '[
             exec clj clojure.lang.Script "$0" -- "$@"
             ]

(ns user)

;; http://projecteuler.net/index.php?section=problems&id=31
;;
;; Problem 31
;; 22 November 2002

;; In England the currency is made up of pound, £, and pence, p,
;; and there are eight coins in general circulation:
;;
;;     1p, 2p, 5p, 10p, 20p, 50p, £1 (100p) and £2 (200p).
;;
;; It is possible to make £2 in the following way:
;;
;;     1×£1 + 1×50p + 2×20p + 1×5p + 1×2p + 3×1p
;;
;; How many different ways can £2 be made using any number of coins?


(def coins '(100 50 20 5 2 1))

(defn set-nth
  "set the nth item of sequence, counted 0...n-1"
  [sequ ndx item]
  (concat (take (dec ndx) sequ) [1] (drop ndx sequ)))

(defn generate-partitions
  "incomplete"
  [n]
  (let [x (ref (cons n (for [x (range (dec n))] 1)))
        m (ref 1)
        h (ref 1)]
    (println (take 1 @x))
    (dosync
     (while (not (= (first @x) 1))
       (if (= (nth @x (dec @h)) 2)
         (do
           (ref-set m (inc @m))
           (ref-set x (set-nth @x (dec @h) 1))
           (ref-set h (- @h 1)))
         (do
           (let [r (- (nth @x (- @h 1)) 1)
                 t (ref (inc (- @m @h)))]
             (ref-set x (set-nth @x (dec @h) r))
             (while (>= @t r)
               (ref-set h (inc @h))
               (ref-set x (set-nth @x (dec @h) r))
               (ref-set t (- @t @r)))

             (if (= @t 0)
               (ref-set m @h)
               (do
                 (ref-set m (inc @h))
                 (if (> @t 1)
                   (ref-set h (inc @h))
                   (ref-set x (set-nth @x (dec @h) @t))))))))
       (println @x)))))


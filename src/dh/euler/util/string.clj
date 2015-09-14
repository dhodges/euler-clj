(ns dh.euler.util.string)

(defn strcontains?
  [s chr]
  (< -1 (.indexOf s (str chr))))


(defn str-pandigital?
  [nstr]
  (reduce #(and %1 %2)
          (for [i (range 1 (inc (count nstr)))]
            (strcontains? nstr (char (+ 48 i))))))

(defn palindrome?
  [x]
  (let [s (str x)]
    (= (seq s) (reverse s))))

(defn str-rotate-left
  "rotate n characters left"
  ([s] (str-rotate-left s 1))
  ([s n]
     (assert (>= n 0))
     (let [n (rem n (count s))]
       (cond (= n 0)
             s
             (= n (count s))
             s
             :else
             (str
              (.substring s n)
              (.substring s 0 n))))))

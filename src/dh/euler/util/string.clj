(ns dh.euler.util.string)

(defn strcontains?
  [s chr]
  (< -1 (.indexOf s (str chr))))

(defn strcat
  [& items]
  (apply str (apply concat (map str items))))

(defn str-pandigital?
  [nstr]
  (reduce #(and %1 %2)
          (for [i (range 1 (inc (count nstr)))]
            (strcontains? nstr (char (+ 48 i))))))

(defn palindrome?
  [x]
  (let [s (str x)]
    (= (seq s) (reverse s))))

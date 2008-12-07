#^:shebang '[
             exec clj clojure.lang.Script "$0" -- "$@"
             ]

(ns user)

;; Problem 15
;;
;; http://projecteuler.net/index.php?section=problems&id=15
;;
;; Starting in the top left corner of a 2 x 2 grid, there are 6 routes
;; (without backtracking) to the bottom right corner.
;;
;; How many routes are there through a 20 x 20 grid?

(defstruct pair :x :y)

(defn in-range
  [pt endpt]
  (and (<= 0 (pt :x) (endpt :x))
       (<= 0 (pt :y) (endpt :y))))

(defn next-2-points
  "vector of the succeeding two points"
  [pt]
  [(struct pair (pt :x)  (inc (pt :y)))
   (struct pair (inc (pt :x)) (pt :y))])



#! http://en.wikipedia.org/wiki/Depth-first_search

(def *numroutes* (ref 0))

(defn- member?
  [item coll]
  (cond (empty? coll)
        false
        (= item (first coll))
        true
        :else
        (recur item (rest coll))))

(defn- dfs-1
  [path pt endpt]
  (if (and (in-range pt endpt)
           (not (member? pt path)))
    (let [path (concat path [pt])]
      (if (= pt endpt)
        (dosync (ref-set *numroutes* (inc @*numroutes*)))
        (doseq [pt (next-2-points (last path))]
          (dfs-1 path pt endpt))))))

(defn dfs
  [endpt]
  (dosync (ref-set *numroutes* 0))
  (dfs-1 [] (struct pair 0 0) endpt)
  @*numroutes*)


#! http://en.wikipedia.org/wiki/Breadth-first_search
          
(def paths-to-check (ref [[(struct pair 0 0)]]))

(defn bfs
  [endpt]
  (dosync (ref-set paths-to-check [[(struct pair 0 0)]]))
  (let [numroutes (ref 0)]
    (loop []
      (printf "%d paths\n" (count @paths-to-check))
      (if (empty? @paths-to-check)
        @numroutes
        (do

          (doseq [p @paths-to-check]
            (if (= endpt (last p))
              (dosync (ref-set numroutes (inc @numroutes)))))

          (dosync (ref-set paths-to-check 
                           (for [p  @paths-to-check
                                 pt (next-2-points (last p))
                                 :when (in-range pt endpt)]
                             (concat p [pt]))))
          (recur)
          )))))

#! ==========================================================

(defn solution
  [endpt]
  ;(printf "%s routes found." (time (dfs endpt)))
  (printf "%s routes found." (time (bfs endpt)))
  )

(defn test-solution
  []
  (= (solution) "???"))

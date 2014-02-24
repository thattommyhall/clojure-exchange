(ns clojure-exchange.reducers
  (:require [clojure.core.reducers :as r]
            [clojure.java.io :as io]))

(def corpus (slurp "CORPUS"))

(defn count-words [text]
  (reduce
   (fn [acc word]
     (assoc acc word (inc (get acc word 0))))
   {}
   (map #(.toLowerCase %) (into [] (re-seq #"\w+" text)))))

(defn p-count-words [text]
  (r/fold
   (r/monoid (partial merge-with +)
             hash-map)
   (fn [acc word]
     (assoc acc word (inc (get acc word 0))))
   (r/map #(.toLowerCase %) (into [] (re-seq #"\w+" text)))))

(def input (into [] (range 10000000)))
(defn -main []
  ;; (time (reduce + (map inc (filter even? input))))
  ;; (time (reduce + (r/map inc (r/filter even? input))))
  ;; (time (r/fold + (r/map inc (r/filter even? input))))
  (time (count-words corpus))
  (time (p-count-words corpus))
  (println "Done"))

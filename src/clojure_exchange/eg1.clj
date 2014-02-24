(ns clojure-exchange.eg1)

(defn start-thread
  [fn]
  (.start
   (Thread. fn)))

(defn loop-print [n]
  (let [line (apply str n ":**********")]
    (println line)
    (recur n)))

(defn -main []
  (dotimes [n 50]
    (start-thread #(loop-print n))))

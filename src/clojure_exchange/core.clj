(ns clojure-exchange.core)

(defn start-thread
  [fn]
  (.start
   (Thread. fn)))

(defn loop-print
  [n]
  (let [line (apply str n ":**********")]
    (println line)
    (Thread/sleep 20)
    (recur n)))

(defn -main []
  (dotimes [n 50]
    (start-thread #(loop-print n))))

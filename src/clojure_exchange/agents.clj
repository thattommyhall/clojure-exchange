(ns clojure-exchange.agents
  (:require  [clojure.java.io :as io]))

(defn start-thread
  [fn]
  (.start
   (Thread. fn)))

(defn write [w & content]
  (doseq [x (interpose " " content)]
    (.write w (str x)))
  (doto w
    (.write "\n")
    .flush))

(def console (agent  *out*))

(def log-file (agent (io/writer "LOG" :append true)))

(defn loop-print
  [n]
  (let [line (apply str n ":**********")]
    (Thread/sleep (rand 5))
    (send-off console write (str n ":*********"))
    (recur n)))

(defn -main []
  (dotimes [n 100]
    (start-thread #(loop-print n))))

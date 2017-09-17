(ns hours-api.core
  (:require [hours-api.system :as system])
  (:gen-class))

(defn -main [& args]
  (system/start))
  

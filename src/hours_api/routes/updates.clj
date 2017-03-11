(ns hours-api.routes.updates
  (:require
    [compojure.core :refer :all]
    [org.httpkit.server :refer [with-channel on-close]]))

(defonce channels (atom #{}))

(defn- connect! [channel]
  (println "connect channel")
  (swap! channels conj channel))

(defn- disconnect! [channel status]
  (println (str "disconnect channel with status: " status))
  (swap! channels #(remove #{channel} %)))

(defn customer-updates [request]
  (with-channel request channel
    (connect! channel)
    (on-close channel (partial disconnect! channel))))

(defroutes updates
  (context "/updates" []
    (GET "/customer" request (customer-updates request))))

(ns hours-api.components.updates
  (:require
    [com.stuartsierra.component :as component]
    [org.httpkit.server :refer [send!]]
    [hours-api.components.broker :refer [poll]]))

(defonce ^:private channels (atom #{}))

(defn connect-client! [channel]
  (swap! channels conj channel))

(defn disconnect-client! [channel _]
  (swap! channels #(remove #{channel} %)))

(defn- send-clients [event-data]
  (doseq [c @channels]
    (send! c event-data)))

(defn handle-event [record]
  (send-clients (.value record)))

(defrecord Updates [broker]
  component/Lifecycle

  (start [component]
    (println ";; Starting Updates")
    (future (poll broker handle-event)))

  (stop [component]
    (println ";; Stopping Updates")))


(defn new-updates []
  (Updates. nil))

(ns hours-api.routes.updates
  (:require
    [compojure.core :refer :all]
    [org.httpkit.server :refer [with-channel on-close]]
    [hours-api.components.updates :refer [connect-client! disconnect-client!]]))

(defn updates-ws [request]
  (with-channel request channel
    (connect-client! channel)
    (on-close channel (partial disconnect-client! channel))))

(defroutes updates
  (GET "/updates" request (updates-ws request)))

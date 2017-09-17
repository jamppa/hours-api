(ns hours-api.api
  (:require
    [compojure.core :refer :all]
    [hours-api.routes.commands :as commands-routes]
    [hours-api.routes.updates :as updates-routes]))

(defroutes app-api
  (context "/api" []
    commands-routes/commands))
    
(defroutes app-ws
  (context "/ws" []
    updates-routes/updates))

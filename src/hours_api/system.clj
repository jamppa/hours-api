(ns hours-api.system
  (:require
    [com.stuartsierra.component :as component]
    [hours-api.components.env :refer [new-env]]
    [hours-api.components.broker :refer [new-broker]]
    [hours-api.components.updates :refer [new-updates]]
    [hours-api.components.app :refer [new-app]]
    [hours-api.components.server :refer [new-http-server]]))

(defn system []
  (component/system-map
    :env (new-env)
    :broker (component/using (new-broker) [:env])
    :updates (component/using (new-updates) [:broker])
    :app (component/using (new-app) [:broker])
    :http-server (component/using (new-http-server) [:env :app])))

(defn test-system []
  (component/system-map
    :env (new-env)
    :broker (component/using (new-broker) [:env])
    :app (component/using (new-app) [:broker])))

(defn start []
  (component/start (system)))

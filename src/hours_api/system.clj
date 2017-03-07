(ns hours-api.system
  (:require
    [com.stuartsierra.component :as component]
    [hours-api.components.env :refer [new-env]]
    [hours-api.components.broker :refer [new-broker]]
    [hours-api.components.app :refer [new-app]]
    [hours-api.components.server :refer [new-http-server]]))

(defn system []
  (component/system-map
    :env (new-env)
    :broker (new-broker)
    :app (component/using (new-app) [:env :broker])
    :http-server (component/using (new-http-server) [:env :app])))

(defn start []
  (component/start (system)))

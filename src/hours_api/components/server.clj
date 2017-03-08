(ns hours-api.components.server
  (:require
    [com.stuartsierra.component :as component]
    [ring.middleware.format :refer [wrap-restful-format]]
    [org.httpkit.server :as httpkit]
    [hours-api.api :as api]))

(defn wrap-app-component [handler app]
  (fn [req]
    (handler (assoc req :app app))))

(defn make-handler [app]
  (-> api/app-api
      (wrap-app-component app)
      (wrap-restful-format :formats [:json-kw])))

(defrecord HttpServer [env app]
  component/Lifecycle

  (start [component]
    (println ";; Starting Http Server")
      (assoc component :server
        (httpkit/run-server
          (make-handler app) (get-in env [:config :http-server]))))

  (stop [{:keys [server] :as component}]
    (println ";; Stopping Http Server")
    (when server (server))
    (assoc component :server nil))
)

(defn new-http-server []
  (HttpServer. nil nil))

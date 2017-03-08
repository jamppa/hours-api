(ns hours-api.routes.utils)

(defn request-body [request]
  (:body request))

(defn app [request]
  (:app request))

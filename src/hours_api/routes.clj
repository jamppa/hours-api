(ns hours-api.routes
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.util.response :refer :all]))

(defroutes commands-route
  (POST "/commands" [:as request]
    (println (:app request))
    (response {:foo "bar"})))

(defroutes app-routes
  (context "/api" [] commands-route))

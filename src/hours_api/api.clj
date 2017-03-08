(ns hours-api.api
  (:require
    [compojure.core :refer :all]
    [hours-api.routes.commands :as commands-routes]))

(defroutes app-api
  (context "/api" [] commands-routes/commands))

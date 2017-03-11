(ns hours-api.routes.commands
  (:require
    [compojure.core :refer :all]
    [ring.util.response :refer :all]
    [hours-api.commands.customer :as customer-commands]))

(defroutes customer-commands
  (POST "/customer" [request :as {app :app command :params}]
    (customer-commands/handle (:broker app) command)
    (response command)))

(defroutes commands
  (context "/commands" [] customer-commands))

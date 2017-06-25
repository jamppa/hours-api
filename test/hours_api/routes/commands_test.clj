(ns hours-api.routes.commands-test
  (:require
    [midje.sweet :refer :all]
    [ring.mock.request :as ring-mock]
    [hours-api.routes.commands :as commands-routes]
    [hours-api.commands.customer :as customer-commands]
    [hours-api.system :as system]))

(def command {:command "foo"})
(def app-component (:app (system/test-system)))
(def broker-component (:broker app-component))

(defn post [uri app command]
  (->
    (ring-mock/request :post uri)
    (ring-mock/content-type "application/json")
    (merge {:app app :params command})))

(fact "handles customer commands"
  (commands-routes/customer-commands
    (post "/customer" app-component command)) => {:status 200 :body command :headers {}}
    (provided
      (customer-commands/handle broker-component command) => anything))

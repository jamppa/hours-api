(ns hours-api.commands.customer-test
  (:require
    [midje.sweet :refer :all]
    [hours-api.commands.customer :as customer]
    [hours-api.components.broker :as broker]
    [hours-api.system :as system]))

(def broker-component (:broker (system/test-system)))
(def valid-create-customer-cmd
  (customer/new-create-customer-cmd {
    :customer-name "Firma Oy"
    :customer-business-id "12345467-8"
    }))

(fact "valid create customer command is sent to broker"
  (customer/handle broker-component valid-create-customer-cmd) => anything
    (provided
      (broker/send-command broker-component valid-create-customer-cmd) => anything))

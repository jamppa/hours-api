(ns hours-api.commands.create-customer-test
  (:require
    [midje.sweet :refer :all]
    [hours-api.commands.customer :as customer]
    [hours-api.components.broker :as broker]
    [hours-api.system :as system]))

(def broker-component (:broker (system/test-system)))
(def valid-data {:customer-name "Firma Oy"
                 :customer-business-id "12345467-8"})


(def valid-cmd
  (customer/new-create-customer-cmd valid-data))


(def invalid-cmd-missing-name
  (customer/new-create-customer-cmd
    (merge valid-data {:customer-name nil})))


(def invalid-cmd-missing-business-id
  (customer/new-create-customer-cmd
    (merge valid-data {:customer-business-id nil})))


(fact "valid create customer -command is sent to broker"
  (customer/handle broker-component valid-cmd) => anything
    (provided
      (broker/send-command broker-component valid-cmd) => anything))


(fact "invalid create customer -command missing customer name fails and is not sent to broker"
  (customer/handle broker-component invalid-cmd-missing-name) => (throws Throwable)
  (provided
    (broker/send-command anything anything) => anything :times 0))


(fact "invalid create customer -command missing business id name fails and is not sent to broker"
  (customer/handle broker-component invalid-cmd-missing-business-id) => (throws Throwable)
  (provided
    (broker/send-command anything anything) => irrelevant :times 0))

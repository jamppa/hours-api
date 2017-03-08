(ns hours-api.commands.customer
  (:require
    [hours-api.components.broker :as broker]
    [clojure.spec :as s]))

(def topic "customer")
(defmulti handle (fn [broker command] (:type command)))

(s/def ::customer-name string?)
(def create-customer-spec
  (s/keys :req-un [::customer-name]))

(defmethod handle "create-customer" [broker command]
  (when-not (s/valid? create-customer-spec (:data command))
            (throw (Exception. "invalid create-customer command")))
  (broker/send-command broker topic command))

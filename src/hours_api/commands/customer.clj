(ns hours-api.commands.customer
  (:require
    [hours-api.components.broker :as broker]
    [hours-api.commands.utils :as utils]
    [clojure.spec :as s]
    [slingshot.slingshot :refer :all]))

(def topic "pending-customer-cmds")
(defmulti handle (fn [broker command] (:type command)))

(s/def ::customer-name string?)
(s/def ::create-customer-spec
  (s/keys :req-un [::customer-name]))

(defmethod handle "create-customer" [broker command]
  (when-not (s/valid? ::create-customer-spec (:data command))
            (throw+ (utils/invalid-command-ex)))
  (broker/send-command broker topic command))

(ns hours-api.components.app
  (:require [com.stuartsierra.component :as component]))

(defrecord App [broker]
  component/Lifecycle

  (start [component]
    (merge component {
      :broker broker
    }))

  (stop [component]
    (merge component {
      :broker nil
    }))
)

(defn new-app []
  (App. nil))

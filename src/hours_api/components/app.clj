(ns hours-api.components.app
  (:require [com.stuartsierra.component :as component]))

(defrecord App [env broker]
  component/Lifecycle

  (start [component]
    (merge component {
      :env env
      :broker broker
    }))

  (stop [component]
    (merge component {
      :env nil
      :broker nil
    }))
)

(defn new-app []
  (App. nil nil))

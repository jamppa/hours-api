(ns hours-api.components.env
  (:require [com.stuartsierra.component :as component]))

(def default-config {
  :http-server {
    :port 8000
  }
  :broker {
    "bootstrap.servers" "broker:9092"
    "acks" "all"
    "retries" (int 0)
    "batch.size" (int 16384)
    "linger.ms" (int 1)
    "key.serializer" "org.apache.kafka.common.serialization.StringSerializer"
    "value.serializer" "org.apache.kafka.common.serialization.StringSerializer"
  }
})

(defrecord Env [config]
  component/Lifecycle

  (start [component]
    (merge component {:config config}))

  (stop [component]
    (assoc component :config nil))
)

(defn new-env []
  (Env. default-config))

(ns hours-api.components.env
  (:require [com.stuartsierra.component :as component]))

(def default-config {
  :http-server {
    :port 8000
  }
  :broker-producer {
    "bootstrap.servers" "broker:9092"
    "acks" "all"
    "retries" (int 0)
    "batch.size" (int 16384)
    "linger.ms" (int 1)
    "key.serializer" "org.apache.kafka.common.serialization.StringSerializer"
    "value.serializer" "org.apache.kafka.common.serialization.StringSerializer"
  }
  :broker-consumer {
    "bootstrap.servers" "broker:9092"
    "group.id" "hours-api"
    "enable.auto.commit" "false"
    "key.deserializer" "org.apache.kafka.common.serialization.StringDeserializer"
    "value.deserializer" "org.apache.kafka.common.serialization.StringDeserializer"
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

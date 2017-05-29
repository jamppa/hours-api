(ns hours-api.components.env
  (:require
    [com.stuartsierra.component :as component]
    [environ.core :refer [env]]))

(def ^:private broker-host (env :broker-host))

(def ^:private default-config {
  :http-server {
    :port 8000
  }
  :broker-producer {
    "bootstrap.servers" broker-host
    "acks" "all"
    "retries" (int 0)
    "batch.size" (int 16384)
    "linger.ms" (int 1)
    "key.serializer" "org.apache.kafka.common.serialization.StringSerializer"
    "value.serializer" "org.apache.kafka.common.serialization.StringSerializer"
  }
  :broker-consumer {
    "bootstrap.servers" broker-host
    "group.id" "hours-api"
    "enable.auto.commit" "true"
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

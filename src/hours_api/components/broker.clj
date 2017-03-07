(ns hours-api.components.broker
  (:require [com.stuartsierra.component :as component])
  (:import [org.apache.kafka.clients.producer KafkaProducer]))

(def configuration {
  "bootstrap.servers" "broker:9092"
  "acks" "all"
  "retries" (int 0)
  "batch.size" (int 16384)
  "linger.ms" (int 1)
  "key.serializer" "org.apache.kafka.common.serialization.StringSerializer"
  "value.serializer" "org.apache.kafka.common.serialization.StringSerializer"
  })

(defn- new-producer [configuration]
  (KafkaProducer. configuration))

(defrecord Broker [configuration]
  component/Lifecycle

  (start [component]
    (println ";; Starting Broker")
    (assoc component :producer (new-producer configuration)))

  (stop [component]
    (println ";; Stopping Broker")
    (let [producer (:producer component)]
      (.close producer))
    (assoc component :producer nil))
)

(defn new-broker []
  (Broker. configuration))

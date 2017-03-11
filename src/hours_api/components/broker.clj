(ns hours-api.components.broker
  (:require
    [com.stuartsierra.component :as component]
    [cheshire.core :as cheshire])
  (:import
    [org.apache.kafka.clients.producer KafkaProducer ProducerRecord]
    [org.apache.kafka.clients.consumer KafkaConsumer ConsumerRecord]))

(def ^:private pending-cmds-topic "pending-cmds")
(def ^:private accepted-cmds-topic "accepted-cmds")
(def ^:private failed-cmds-topic "failed-cmds")

(defn- new-producer [conf]
  (KafkaProducer. conf))

(defn- new-consumer [conf]
  (KafkaConsumer. conf))

(defn- new-producer-record [topic message]
  (ProducerRecord. topic message))

(defrecord Broker [env]
  component/Lifecycle

  (start [component]
    (println ";; Starting Broker")
    (assoc component
      :producer (new-producer (get-in env [:config :broker-producer]))
      :consumer (new-consumer (get-in env [:config :broker-consumer]))))

  (stop [component]
    (println ";; Stopping Broker")
    (let [producer (:producer component)
          consumer (:consumer component)]
      (.close producer)
      (.close consumer))
    (assoc component
      :producer nil
      :consumer nil))
)

(defn new-broker []
  (Broker. nil))

(defn send-command [broker topic command]
  (let [producer (:producer broker)
        command-json (cheshire/generate-string command)]
    (.send producer (new-producer-record topic command-json))))

(defn subscribe [broker handler & topics]
  )

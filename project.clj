(defproject hours-api "0.1.0-SNAPSHOT"
  :description "Hours API - HTTP API for commands, updates and queries"
  :url ""
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.9.0-alpha14"]
                 [compojure "1.5.1"]
                 [ring/ring-defaults "0.2.1"]
                 [http-kit "2.2.0"]
                 [ring-middleware-format "0.7.2"]
                 [org.apache.kafka/kafka-clients "0.10.2.0"]
                 [com.stuartsierra/component "0.3.2"]
                 [cheshire "5.7.0"]
                 [slingshot "0.12.2"]]
  :main ^:skip-aot hours-api.core
  :target-path "target/%s"
  :profiles
  {
    :uberjar {:aot :all}
    :dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]
                        [midje "1.8.3"]]
          :plugins [[lein-midje "3.2.1"]]}
  })

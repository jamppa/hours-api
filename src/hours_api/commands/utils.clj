(ns hours-api.commands.utils
  (:require
    [slingshot.slingshot :as s]
    [ring.util.response :refer :all]))

(defn invalid-command-ex []
  {:type ::invalid-command})

(defn wrap-invalid-command-ex [handler]
  (fn [req]
    (s/try+
      (handler req)
      (catch [:type ::invalid-command] {:keys [type]}
        (-> (response "invalid command")
            (status 400))))))

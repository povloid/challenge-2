(ns challenge.data.postgres.sources
  (:require
   [clojure.java.jdbc :as jdbc]
   [mount.core :refer [defstate]]
   [hikari-cp.core :as cp]))


(def pool-config
  {:auto-commit        true
   :read-only          false
   :connection-timeout 30000
   :validation-timeout 5000
   :idle-timeout       600000
   :max-lifetime       1800000
   :minimum-idle       10
   :maximum-pool-size  10
   :pool-name          "db-pool"
   :adapter            "postgresql"
   :server-name        "localhost"
   :port-number        5432
   :database-name      "test"
   :username           "user1"
   :password           "paradox"
   :register-mbeans    false})


(defonce db-1
  (let [pool (cp/make-datasource pool-config)]
    {:datasource pool}))

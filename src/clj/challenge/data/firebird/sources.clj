(ns challenge.data.firebird.sources
  (:require
   [clojure.java.jdbc :as jdbc]
   [mount.core :refer [defstate]]
   [hikari-cp.core :as cp]))


(def pool-config
  {:minimum-idle      10
   :maximum-pool-size 10
   :driver-class-name "org.firebirdsql.jdbc.FBDriver"
   :jdbc-url          "jdbc:firebird://localhost:3050//var/lib/firebird/3.0/data/test.fdb"
   :username          "SYSDBA"
   :password          "paradox"
   :char-set          "UTF-8"
   :encoding          "UTF8"})


;; simple variant
;;
(defonce db-1
  (let [pool (cp/make-datasource pool-config)]
    {:datasource pool}))

;; call as @db-2
(defonce db-2
  (delay
    (let [pool (cp/make-datasource pool-config)]
      {:datasource pool})))

;; Variant with mount library.
;;
;; (defstate DB
;;   :start
;;   (let [pool (cp/make-datasource pool-config)]
;;     {:datasource pool})
;;   :stop
;;   (-> DB :datasource cp/close-datasource))

;; need to execute (mount.core/start)

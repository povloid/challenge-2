(ns challenge.providers.stackoverflow
  (:require [clj-http.client :as client]))


(def api-endpoint "https://api.stackexchange.com/2.2")


(def params {:pagesize 100
             :order    "desc"
             :sort     "creation"
             :tagged   "clojure"
             :site     "stackoverflow"})

(defn search [params]
  (let [{:keys [body]} (client/get
                        (str api-endpoint "/search")
                        {:cookie-policy :standard
                         :query-params  params
                         :as            :json})]
    body))

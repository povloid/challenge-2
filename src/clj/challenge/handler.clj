(ns challenge.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.json :refer [wrap-json-body
                                          wrap-json-response]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.keyword-params :refer [wrap-keyword-params]]
            [ring.util.response :refer [response
                                        redirect]]
            [taoensso.timbre :as log]
            [challenge.service :as service]))

(defroutes handler
  (GET "/" req
       (redirect "/index.html"))

  (GET "/search"
        {{:keys [tag]} :params :as request}
        (log/info ">>> GET > " request)
        (-> (if (vector? tag) tag [tag])
            (service/search-by-tags-stat)
            (response)))

  (route/resources "/")
  (route/not-found "<h1>Page not found</h1>"))


(def app (-> handler
             (wrap-keyword-params)
             (wrap-params)
             (wrap-json-body {:keywords? true :bigdecimals? true})
             (wrap-json-response)))

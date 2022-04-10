(ns challenge.events
  (:require [ajax.core :refer [GET]]
            [challenge.db :as db]
            [re-frame.core :refer [dispatch reg-event-db reg-event-fx reg-fx]]
            [taoensso.timbre :as log]))

(reg-event-fx
 :initialize-db
 (fn [_ _]
   {:db db/default-db}))


(reg-event-db
 ::set-input-s1-value
 (fn [db [_ value]]
   (assoc db :input-s1 value)))

(defn valid? [value]
  (.test #"^(\w| )+$" value))

(reg-event-fx
 ::query
 (fn [{{:keys [input-s1] :as db} :db} _]
   (let [input-s1-valid? (valid? input-s1)
         db              (-> db
                             (dissoc :alert
                                     :input-s1-helper))]
     (if input-s1-valid?
       {:db    db
        ::get {:tag (clojure.string/split input-s1 " ")}}
       {:db (cond-> (-> db
                        (assoc :alert {:type    :danger
                                       :message "Bad input values"}))
              (not input-s1-valid?) (assoc :input-s1-helper {:type    :danger
                                                             :message "Bad value"}))}))))

(reg-fx
 ::get
 (fn [params]
   (GET "/search"
         {:params          params
          :format          :json
          :response-format :json
          :keywords?       true
          :handler         #(dispatch [::get-success %])
          :error-handler   #(dispatch [::get-faliure %])})))

(reg-event-db
 ::get-success
 (fn [db [_ result]]
   (log/info result)
   (-> db
       (assoc :alert {:type    :success
                      :message "The following result is obtained"}))))

(reg-event-db
 ::get-failure
 (fn [db [_ error]]
   (log/error error)
   (-> db
       (assoc :alert {:type    :danger
                      :message (str "Error: " error)}))))

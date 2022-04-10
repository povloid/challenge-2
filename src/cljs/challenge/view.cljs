(ns challenge.view
  (:require [challenge.events :as events]
            [challenge.subs :as subs]
            [re-frame.core :refer [dispatch subscribe]]))

(defn input-helper [{:keys [type
                            message]}]
  (case type
    :danger  [:p.text-danger
              message]
    :success [:p.text-success
              message]
    nil))

(defn main-page []
  [:div.container.pt-4
   [:h1 "Statistics by tags"]
   [:div.form
    [:div.form-group
     [:label "Query tags string"]
     [:input.form-control
      {:placeholder "Enter tags"
       :auto-focus  true
       :on-change   #(dispatch [::events/set-input-s1-value (.. % -target -value)])
       :value       @(subscribe [::subs/input-s1-value])}]
     (when-let [o @(subscribe [::subs/input-s1-helper])]
       [input-helper o])]
    [:button.btn.btn-primary
     {:on-click #(dispatch [::events/query])}
     "Execute request"]]
   [:div.mt-4
    (let [{:keys [type
                  message]} @(subscribe [::subs/alert])]
      (case type
        :success [:div.alert.alert-success
                  message]
        :info    [:div.alert.alert-info
                  message]
        :danger  [:div.alert.alert-danger
                  message]
        nil))]
   [:br]
   [:table {:class "table"}
    [:thead
     [:tr
      [:th {:scope "col"} "#"]
      [:th {:scope "col"} "Tag"]
      [:th {:scope "col"} "Total"]
      [:th {:scope "col"} "Answered"]]]
    [:tbody
     (->> @(subscribe [::subs/items])
          (map-indexed (fn [i {:keys [tag
                                      total
                                      answered]}]
                         [:tr
                          [:th {:scope "row"} (inc i)]
                          [:td tag]
                          [:td total]
                          [:td answered]]))
          (into [:<>]))]]])

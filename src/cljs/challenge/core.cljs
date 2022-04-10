(ns challenge.core
  (:require [challenge.view :refer [main-page]]
            [re-frame.core :refer [clear-subscription-cache! dispatch-sync]]
            [reagent.dom :as rdom]))

(enable-console-print!)

(defn root-component []
  [main-page])

(defn mount-root []
  (clear-subscription-cache!)
  (rdom/render
   [root-component]
   (js/document.getElementById "app-container")))

(defn ^:export init []
  (dispatch-sync [:initialize-db])
  (mount-root))

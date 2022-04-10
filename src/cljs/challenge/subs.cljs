(ns challenge.subs
  (:require [re-frame.core :refer [reg-sub]]))

(reg-sub
 ::input-s1-value
 (fn [db]
   (db :input-s1)))

(reg-sub
 ::input-s1-helper
 :input-s1-helper)

(reg-sub
 ::alert
 :alert)

(reg-sub
 ::items
 :items)

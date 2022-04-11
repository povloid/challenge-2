(ns challenge.service
  (:require [challenge.providers.stackoverflow :as stackoverflow]))


(def pagesize 100)
(def max-requests-per-once 5)


(defn search-by-tags [tags]
  (->> tags
       (map (fn [tag]
              {:pagesize pagesize
               :order    "desc"
               :sort     "creation"
               :tagged   tag
               :site     "stackoverflow"}))
       (partition-all max-requests-per-once)
       (map (partial pmap (fn [params]
                            {:params params
                             :result (stackoverflow/search params)})))
       (reduce into [])))

(defn make-search-stat [{{:keys [tagged]} :params
                         {:keys [items]}  :result}]
  {:tag      tagged
   :total    (count items)
   :answered (reduce #(+ %1 (if (%2 :is_answered) 1 0)) 0 items)})

(defn search-by-tags-stat [tags]
  (->> tags
       (search-by-tags)
       (map make-search-stat)
       (reduce (fn [a {:keys [tag total answered]}]
                 (assoc a tag {:total    total
                               :answered answered})) {})))

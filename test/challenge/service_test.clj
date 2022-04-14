(ns challenge.service-test
  (:require [clojure.test :refer :all]
            [challenge.service :as service]))


(deftest test-search-by-tags
  (testing "test search by tags"
    (let [result (service/search-by-tags
                  ["linux" "windows"])]
      (is (vector? result))
      (is (not (empty? result)))
      (is (-> result count (= 2))))))

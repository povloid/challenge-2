(ns challenge.providers.stackoverflow-test
  (:require [clojure.test :refer :all]
            [challenge.providers.stackoverflow :as stackoverflow]))


(deftest test-search-request
  (testing "test search request"
    (let [result (stackoverflow/search {:pagesize 10
                                        :order    "desc"
                                        :sort     "creation"
                                        :tagged   "linux"
                                        :site     "stackoverflow"})]
      (is (not (empty? result)))
      (is (map? result))
      (is (-> result :items vector?))
      (is (-> result :items count (= 10))))))

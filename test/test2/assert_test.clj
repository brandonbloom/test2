(ns test2.assert-test
  (:require [clojure.test :refer :all]
            [test2.api.runners :refer [run-test-fn]]
            [test2.expect :refer :all]))

(defn ^:test real-test-1 []
  (expect empty? [])
  (expect empty? [1 2 (+ 1 2)]))

(deftest testing-assert
  (let [result (run-test-fn #'real-test-1)]
    (is (= {:test #'real-test-1
            :results [{:status :pass
                       :file "assert_test.clj"
                       :line 7}
                      {:status :fail
                       :file "assert_test.clj"
                       :line 8
                       :failure-details {:result false
                                         :fn 'empty?
                                         :args [[1 2 3]]
                                         :raw-args '[[1 2 (+ 1 2)]]}}]}
           result))))

(defn test-ns-hook []
  (testing-assert))

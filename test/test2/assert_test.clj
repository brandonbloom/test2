(ns test2.assert-test
  (:require [clojure.test :refer :all]
            [test2.api.runners :refer [run-test-fn]]
            [test2.expect :refer :all]))

(defn ^:test real-test-1 []
  (expect empty? [])
  (expect empty? [1 2 (+ 1 2)])
  (expect empty? (/ 1 0)))

(deftest testing-assert
  (let [test-result (run-test-fn #'real-test-1)
        assertion-results (:results test-result)]
    (is (= [:test :results] (keys test-result)))
    (is (= #'real-test-1
           (:test test-result)))
    (is (= 3 (count assertion-results)))
    (let [[r1 r2 r3] assertion-results]
      (is (= {:status :pass
              :file "assert_test.clj"
              :line 7}
             r1))
      (is (= {:status :fail
              :file "assert_test.clj"
              :line 8
              :failure-details {:result false
                                :fn 'empty?
                                :args [[1 2 3]]
                                :raw-args '[[1 2 (+ 1 2)]]}}
             r2))
      ;; (is (= {:status :error
      ;;         :file "assert_test.clj"
      ;;         :line 7
      ;;         :exception 4}
      ;;        r3))
      )))

(defn test-ns-hook []
  (testing-assert))

(ns test2.core-test
  (:require [clojure.test :refer :all]
            [test2.core]))

(defn ^:test fake-test-1 []
  "hi")

(defn fake-test-2 []
  "bye")

(deftest testing-test-fns-in-ns
  (let [fns (#'test2.core/test-fns-in-ns 'test2.core-test)]
    (prn fns)
    (is (= [#'fake-test-1] fns))))

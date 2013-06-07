(ns test2.core-test
  (:require [clojure.test :refer :all]
            [test2.fake-test :refer :all]
            [test2.core]))

(deftest testing-test-fns-in-ns
  (let [fns (#'test2.core/test-fns-in-ns 'test2.fake-test)]
    (is (= #{#'fake-test-1
             #'fake-test-3}
           (set fns)))))

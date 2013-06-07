(ns test2.core-test
  (:require [clojure.test :refer :all]
            [test2.core]))

(deftest testing-find-test-fns
  (let [fns (#'test2.core/find-test-fns)]
    (prn fns)
    (is (= 0 1))))

(deftest testing-all-namespaces-in-project
  (let [nss (#'test2.core/all-namespaces-in-project)]
    (prn nss)
    (is (= 0 1))))

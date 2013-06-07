(ns test2.run-test
  (:require [clojure.test :refer :all]
            [test2.fake-test]
            [test2.run]))

(deftest testing-test-fns-in-ns
  (let [fns (#'test2.run/test-fns-in-ns 'test2.fake-test)]
    (is (= #{#'test2.fake-test/fake-test-1
             #'test2.fake-test/fake-test-3}
           (set fns)))))

(deftest testing-running-high-level
  (let [s (with-out-str
            (test2.run/run-tests :namespaces ['test2.failing-test]))]
    (is (.contains s "FAIL")))
  (let [s (with-out-str
            (test2.run/run-tests :namespaces ['test2.passing-test]))]
    (is (not (.contains s "FAIL")))))

(defn test-ns-hook []
  (testing-test-fns-in-ns)
  (testing-running-high-level))

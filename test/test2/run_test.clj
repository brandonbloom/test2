(ns test2.run-test
  (:require [clojure.test :refer :all]
            [test2.fake-test]
            [test2.run]
            [test2.default.reporter :refer [exit-with-code]]))

(deftest testing-test-fns-in-ns
  (let [fns (#'test2.run/test-fns-in-ns 'test2.fake-test)]
    (is (= #{#'test2.fake-test/fake-test-1
             #'test2.fake-test/fake-test-3}
           (set fns)))))

(deftest testing-running-high-level
  (let [exit-code (ref nil)]
    (with-redefs [exit-with-code #(dosync (ref-set exit-code %))]
      (let [s (with-out-str
                (test2.run/run-tests :namespaces ['test2.failing-test]))]
        (is (.contains s "FAIL"))
        (is (.contains s "Ran 3 tests containing 7 assertions"))
        (is (.contains s "6 failures, 0 errors"))
        (is (= @exit-code 1)))
      (let [s (with-out-str
                (test2.run/run-tests :namespaces ['test2.passing-test]))]
        (is (not (.contains s "FAIL")))
        (is (.contains s "Ran 1 tests containing 1 assertions"))
        (is (.contains s "0 failures, 0 errors"))
        (is (= @exit-code 0))))))

(defn test-ns-hook []
  (testing-test-fns-in-ns)
  (testing-running-high-level))

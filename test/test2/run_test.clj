(ns test2.run-test
  (:require [clojure.test :refer :all]
            [test2.fake-test]
            [test2.run]
            [test2.default.reporter :as r]))

(deftest testing-test-fns-in-ns
  (let [fns (#'test2.api.runners/test-fns-in-ns 'test2.fake-test)]
    (is (= #{#'test2.fake-test/fake-test-1
             #'test2.fake-test/fake-test-3}
           (set fns)))))

(deftest testing-running-high-level
  (let [exit-code (ref nil)]
    (with-redefs [r/exit-with-code #(dosync (ref-set exit-code %))]
      (let [s (with-out-str
                (test2.run/run-tests :namespaces ['test2.failing-test]))]
        (is (.contains s "TEST FAILED"))
        (is (.contains s "Ran 4 tests containing 8 assertions"))

        (is (.contains s "    FAIL: (= 1 2)"))
        (is (.contains s "Expected: (= 1 2)"))

        (is (.contains s "In failing_test.clj at line 7"))
        (is (.contains s "    FAIL: (empty? [1 2 (+ 1 2)])"))
        (is (.contains s "Expected: (empty? [1 2 3])"))
        (is (.contains s "     Got: false"))

        (is (.contains s "In failing_test.clj at line 20"))
        (is (.contains s "   ERROR: (empty? (/ 1 0))"))
        (is (.contains s "     Got: java.lang.ArithmeticException: Divide by zero"))

        (is (.contains s "6 failures, 1 errors"))
        (is (= @exit-code 1)))
      (let [s (with-out-str
                (test2.run/run-tests :namespaces ['test2.passing-test]))]
        (is (not (.contains s "TEST FAILED")))
        (is (not (.contains s "FAIL")))
        (is (.contains s "Ran 1 tests containing 1 assertions"))
        (is (.contains s "0 failures, 0 errors"))
        (is (= @exit-code 0))))))

(defn test-ns-hook []
  (testing-test-fns-in-ns)
  (testing-running-high-level))

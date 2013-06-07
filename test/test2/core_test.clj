(ns test2.core-test
  (:require [clojure.test :refer :all]
            [test2.core]))

(deftest a-test
  (testing "FIXME, I fail."
    (let [fns (#'test2.core/find-test-fns)]
      (is (= 0 1)))))

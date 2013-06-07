(ns test2.expect-test
  (:require [clojure.test :refer :all]
            [test2.expect :as p]))

(p/deftest fake-deftest-1
  (+ 1 2))

(p/deftest fake-deftest-2
  "foo"
  (+ 1 2))

(deftest testing-new-deftest
  (is (= 'fake-deftest-1 (:name (meta #'fake-deftest-1))))
  (is (= nil (:doc (meta #'fake-deftest-1))))
  (is (= 3 (fake-deftest-1)))

  (is (= 'fake-deftest-2 (:name (meta #'fake-deftest-2))))
  (is (= "foo" (:doc (meta #'fake-deftest-2))))
  (is (= 3 (fake-deftest-2))))

(defn test-ns-hook []
  (testing-new-deftest))

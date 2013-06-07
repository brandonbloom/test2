(ns test2.def-test
  (:require [clojure.test :refer :all]
            [test2.def :as p]))

(p/deftest fake-deftest-1
  (+ 1 2))

(p/deftest fake-deftest-2
  "foo"
  (+ 1 2))

(deftest testing-deftest
  (is (:test (meta #'fake-deftest-1)))
  (is (= 'fake-deftest-1 (:name (meta #'fake-deftest-1))))
  (is (= nil (:doc (meta #'fake-deftest-1))))
  (is (= 3 (fake-deftest-1)))

  (is (:test (meta #'fake-deftest-2)))
  (is (= 'fake-deftest-2 (:name (meta #'fake-deftest-2))))
  (is (= "foo" (:doc (meta #'fake-deftest-2))))
  (is (= 3 (fake-deftest-2))))

(p/defspec "really cool test"
  (+ 1 2))

(p/defspec "it, even has.. bad chars in the name!!!"
  (+ 1 2))

(deftest testing-defspec
  (is (:test (meta #'really-cool-test)))
  (is (= 'really-cool-test (:name (meta #'really-cool-test))))
  (is (= "really cool test" (:doc (meta #'really-cool-test))))
  (is (= 3 (really-cool-test)))

  (is (= 'it-even-has-bad-chars-in-the-name (:name (meta #'it-even-has-bad-chars-in-the-name))))
  (is (= "it, even has.. bad chars in the name!!!" (:doc (meta #'it-even-has-bad-chars-in-the-name))))
  (is (= 3 (it-even-has-bad-chars-in-the-name))))

(defn test-ns-hook []
  (testing-deftest)
  (testing-defspec))

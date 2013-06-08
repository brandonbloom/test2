(ns test2.mimic.ct-test
  (:require [clojure.test :as ct]))

(ct/deftest once-fixtures
  (ct/is (= "f1 t1 t2 f1" (run-tests-in-namespace 'test2.mimic.ct-test))))

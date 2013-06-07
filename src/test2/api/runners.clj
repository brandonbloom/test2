(ns test2.api.runners
  "For writing test-runners."
  (:require [test2.api.asserters :refer [*assertions-results*]]))

(defn run-test-fn
  "Turns a test-fn into a test-result."
  [test-fn]
  (binding [*assertions-results* (ref [])]
    (test-fn)
    {:test test-fn
     :results @*assertions-results*}))

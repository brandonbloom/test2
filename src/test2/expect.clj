(ns test2.expect
  (:require [clojure.string :as s]
            [test2.api.asserters :refer [report-error report-pass report-fail]]))

(defn -expect [f args results])

(defmacro expect
  "Runs (apply f args) and reports on the result."
  [f & args]
  ;; ...
  (try
    (catch Exception e
      (report-error nil e))))

(def ^{:doc "Nicer way of saying identity"}
  truthy? identity)

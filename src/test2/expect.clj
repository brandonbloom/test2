(ns test2.expect
  (:require [clojure.string :as s]
            [test2.api.assertions :refer [*assertions-results*]]))

(defmacro expect
  "Runs (apply f args) and reports on the result."
  [f & args]
  ;; does something with *assertions-results*
  )

(def ^{:doc "Nicer way of saying identity"}
  truthy? identity)

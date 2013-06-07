(ns test2.expect
  (:require [clojure.string :as s]
            [test2.api.assertions :refer [*assertions-results*]]))

(defmacro expect
  "Takes a function and some args, evals, them, and reports on them."
  [f & args]
  ;; does something with *assertions-results*
  )

(def ^{:doc "Nicer way of saying identity"}
  truthy? identity)

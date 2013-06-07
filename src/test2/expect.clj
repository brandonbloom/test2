(ns test2.expect
  (:require [clojure.string :as s]))

(defmacro expect
  "Takes a function and some args, evals, them, and reports on them."
  [f & args]
  )

(def ^{:doc "Nicer way of saying identity"}
  truthy? identity)

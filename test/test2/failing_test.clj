(ns test2.failing-test
  (:require [test2.expect :refer [expect]]))

(defn test-ns-hook [])

(defn ^:test real-test-1 []
  (expect empty? [1 2 3]))

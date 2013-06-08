(ns test2.data.passing-test
  (:require [test2.expect :refer [expect]]))

(defn test-ns-hook [])

(defn ^:test real-test-1 []
  (expect empty? []))

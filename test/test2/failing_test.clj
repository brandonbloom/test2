(ns test2.failing-test
  (:require [test2.expect :refer [expect]]))

(defn test-ns-hook [])

(defn ^:test real-test-1 []
  (expect empty? [1 2 3]))

(defn ^:test real-test-2 []
  (expect empty? [1 2 3])
  (expect empty? [1 2]))

(defn ^:test real-test-3 []
  (expect empty? [1 2 3])
  (expect empty? [1 2])
  (expect empty? [1])
  (expect empty? []))

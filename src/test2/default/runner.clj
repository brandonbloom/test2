(ns test2.default.runner
  (:require [test2.api.runners :refer [run-test-fn]]))

(defn default-runner
  "Runs all tests in linear order."
  [test-fns]
  (map run-test-fn test-fns))

(defn randomized-runner
  "Runs all tests in random order."
  [test-fns]
  (map run-test-fn (shuffle test-fns)))

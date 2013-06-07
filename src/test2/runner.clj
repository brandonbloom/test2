(ns test2.runner)

(defn run-test-fn
  "Turns a test-fn into a test-result."
  [test-fn]
  nil)

(defn default-runner
  "Runs all tests in linear order."
  [test-fns]
  (map run-test-fn test-fns))

(defn randomized-runner
  "Runs all tests in random order."
  [test-fns]
  (map run-test-fn (shuffle test-fns)))

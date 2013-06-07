(ns test2.runner)

(defn run-test-fn
  "Turns a test-fn into a test-result."
  [test-fn]
  nil)

(defn default-runner [test-fns]
  (map run-test-fn test-fns))

(defn randomized-runner [test-fns]
  (map run-test-fn (shuffle test-fns)))

(ns test2.default.runner
  "Some built-in test-runners."
  (:require [test2.api.runners :refer [run-test-fn find-test-fns test-fn-passes-matcher?]]))

(defn- runner [reporter ns-syms matcher-fn sort-test-fns-by]
  (let [test-fns (->> (find-test-fns ns-syms)
                      (filter (partial test-fn-passes-matcher? matcher-fn))
                      (sort-test-fns-by))]
    (reporter (doall (map run-test-fn test-fns)))))

(defn linear-runner
  "Runs all tests in linear order."
  [reporter ns-syms matcher-fn]
  (runner reporter ns-syms matcher-fn identity))

(defn randomized-runner
  "Runs all tests in random order."
  [reporter ns-syms matcher-fn]
  (runner reporter ns-syms matcher-fn shuffle))

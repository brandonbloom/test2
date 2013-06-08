(ns test2.default.runner
  "Some built-in test-runners."
  (:require [test2.api.runners :refer [run-test-fn find-test-fns]]))

(defn maybe-apply-matcher-fn [test-fns matcher-fn]
  (if matcher-fn
    (filter (comp matcher-fn meta) test-fns)
    test-fns))

(defn- runner [ns-syms matcher-fn order-fn]
  (map run-test-fn
       (-> (find-test-fns ns-syms)
           (maybe-apply-matcher-fn matcher-fn)
           (order-fn))))

(defn linear-runner
  "Runs all tests in linear order."
  [ns-syms matcher-fn]
  (runner ns-syms matcher-fn identity))

(defn randomized-runner
  "Runs all tests in random order."
  [ns-syms matcher-fn]
  (runner ns-syms matcher-fn shuffle))

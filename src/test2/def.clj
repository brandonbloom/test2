(ns test2.def
  "Convenience functions for defining tests."
  (:require [clojure.string :as s]))

(defmacro deftest
  "Shortcut for (defn ^:test fn-name ...)
  Optional docstrings are respected."
  [fn-name & body]
  (let [[docstring body] (if (string? (first body))
                           [(first body) (rest body)]
                           [nil body])]
    `(defn ~(with-meta fn-name {:doc docstring}) []
       ~@body)))

(defmacro defspec
  "Like deftest, but the fn-name is generated for you."
  [docstring & body]
  (let [fn-name (-> docstring
                    (s/lower-case)
                    (s/replace " " "-")
                    (s/replace #"[^a-z-]" "")
                    (symbol))]
    `(defn ~(with-meta fn-name {:doc docstring}) []
       ~@body)))

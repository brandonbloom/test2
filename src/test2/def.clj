(ns test2.def
  (:require [clojure.string :as s]))

(defmacro deftest
  "Shortcut for (defn ^:test fn-name ...)"
  [fn-name & body]
  (let [[docstring body] (if (string? (first body))
                           [(first body) (rest body)]
                           [nil body])]
    `(defn ~(with-meta fn-name {:doc docstring}) []
       ~@body)))

(defmacro defspec
  "Like deftest, but auto-generates the fn-name for you based on the docstring."
  [docstring & body]
  (let [fn-name (-> docstring
                    (s/lower-case)
                    (s/replace " " "-")
                    (s/replace #"[^a-z-]" "")
                    (symbol))]
    (prn fn-name)
    `(defn ~(with-meta fn-name {:doc docstring}) []
       ~@body)))

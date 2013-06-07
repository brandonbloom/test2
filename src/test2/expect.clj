(ns test2.expect)

(defmacro deftest
  "Shortcut for (defn ^:test name ...)"
  [name & body]
  (let [[docstring body] (if (string? (first body))
                           [(first body) (rest body)]
                           [nil body])]
    `(defn ~(with-meta name {:doc docstring}) []
       ~@body)))

(defmacro expect
  "Takes a function and some args, evals, them, and reports on them."
  [f & args]
  )

(def ^{:doc "Nicer way of saying identity"}
  truthy? identity)

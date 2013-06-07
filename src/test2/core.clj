(ns test2.core)

(defn run-all-tests []
  )

(defn run-ns-tests [& namespaces]
  )

(defn run-matching-tests
  "Runs only tests whose metadata passes (f)"
  [f]
  )

(defn run-suite-tests
  "Runs only tests in the named suite."
  [suite-name]
  ;; lookup suite fn, and call run-matching-tests with it.
  )

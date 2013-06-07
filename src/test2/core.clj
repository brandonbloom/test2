(ns test2.core)

(defn run-all-tests [& {:keys [runner reporter]}]
  )

(defn run-ns-tests [namespaces & {:keys [runner reporter]}]
  )

(defn run-matching-tests
  "Runs only tests whose metadata passes (f)"
  [f & {:keys [runner reporter]}]
  )

(defn run-suite-tests
  "Runs only tests in the named suite."
  [suite-name & {:keys [runner reporter]}]
  ;; lookup suite fn, and call run-matching-tests with it.
  )

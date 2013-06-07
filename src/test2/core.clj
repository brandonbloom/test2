(ns test2.core)

(defn- run-tests [runner reporter test-fns]

  )

;; the following just call (run-tests)

(defn run-all-tests [& {:keys [runner reporter]}]
  )

(defn run-ns-tests [namespaces & {:keys [runner reporter]}]
  )

(defn run-matching-tests
  "Runs only tests whose metadata passes (f)"
  [f & {:keys [runner reporter]}]
  )

(defn run-tests-in-suite
  "Runs only tests in the named suite."
  [suite & {:keys [runner reporter]}]
  ;; lookup suite fn, and call run-matching-tests with it.
  )

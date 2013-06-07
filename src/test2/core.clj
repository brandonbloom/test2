(ns test2.core
  (:require [test2.helpers :refer [get-runner get-reporter]]))

(defn- run-tests [runner reporter test-fns]
  (let [runner (or runner (get-runner))
        reporter (or reporter (get-reporter))]
    (-> test-fns
        (runner)
        (reporter))))

;; the following just call (run-tests)

(defn run-all-tests [& {:keys [runner reporter]}]
  )

(defn run-ns-tests [namespaces & {:keys [runner reporter]}]
  )

(defn run-matching-tests
  "Runs only tests whose metadata passes (f metadata)"
  [f & {:keys [runner reporter]}]

  )

;; (defn run-tests-in-suite
;;   "Runs only tests in the named suite."
;;   [suite & {:keys [runner reporter]}]
;;   ;; lookup suite fn, and call run-matching-tests with it.
;;   )

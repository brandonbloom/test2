(ns test2.core
  (:require [bultitude.core :as b]
            [test2.runner :refer [default-runner]]
            [test2.reporter :refer [default-reporter]]))

;; this does the main work

(defn- run-tests [runner reporter test-fns]
  (let [runner (or runner (default-runner))
        reporter (or reporter (default-reporter))]
    (-> test-fns
        (runner)
        (reporter))))


;; the following are only needed for the high-level convenience fns

(defn- test-fns-in-ns [ns]
  (require ns)
  ;; find all vars inside ns.
  ;; filter only which have ^:test
  [])

(defn- find-test-fns
  "Returns seq of test-fns within your project."
  ([in-namespaces]
     (mapcat test-fns-in-ns in-namespaces))
  ([]
     (find-test-fns (b/namespaces-on-classpath :classpath "src:test"))))


;; the following just call (run-tests)

(defn run-all-tests [& {:keys [runner reporter]}]
  (run-tests runner reporter (find-test-fns)))

(defn run-ns-tests [namespaces & {:keys [runner reporter]}]
  (run-tests runner reporter (find-test-fns namespaces)))

(defn run-matching-tests
  "Runs only tests whose metadata passes (f metadata)"
  [f & {:keys [runner reporter]}]
  (run-tests runner reporter (filter f (find-test-fns))))

;; (defn run-tests-in-suite
;;   "Runs only tests in the named suite."
;;   [suite & {:keys [runner reporter]}]
;;   ;; lookup suite fn, and call run-matching-tests with it.
;;   )

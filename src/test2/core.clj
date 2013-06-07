(ns test2.core
  (:require [bultitude.core :as b]
            [test2.runner :refer [default-runner]]
            [test2.reporter :refer [default-reporter]]))

(defn- run-tests [runner reporter test-fns]
  (let [runner (or runner (default-runner))
        reporter (or reporter (default-reporter))]
    (-> test-fns
        (runner)
        (reporter))))

(defn- test-fns-in-ns [ns]
  (require ns)
  (->> ns
       (ns-publics)
       (vals)
       (filter (comp :test meta))))

(defn- find-test-fns
  "Returns seq of test-fns within your project."
  ([in-namespaces]
     (mapcat test-fns-in-ns in-namespaces))
  ([]
     (find-test-fns (b/namespaces-on-classpath :classpath "src:test"))))

(defn run-all-tests
  "Runs all tests in current project.

  Both runner and reporter are fns conforming to SPEC."
  [& {:keys [runner reporter]}]
  (run-tests runner reporter (find-test-fns)))

(defn run-ns-tests
  "Runs all tests in the given namespaces.

  Both runner and reporter are fns conforming to SPEC."
  [namespaces & {:keys [runner reporter]}]
  (run-tests runner reporter (find-test-fns namespaces)))

(defn run-matching-tests
  "Runs all tests in project whose metadata passes (f metadata).

  Both runner and reporter are fns conforming to SPEC."
  [f & {:keys [runner reporter]}]
  (run-tests runner
             reporter
             (filter (comp f meta)
                     (find-test-fns))))

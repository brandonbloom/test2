(ns test2.run
  (:require [bultitude.core :as b]
            [test2.default.runner :refer [default-runner]]
            [test2.default.reporter :refer [default-reporter]]))

(defn- run-tests [runner reporter test-fns]
  (let [runner (or runner (default-runner))
        reporter (or reporter (default-reporter))]
    (-> test-fns
        (runner)
        (reporter))))

(defn- test-fns-in-ns
  "Given a ns-symbol, return a seq of test-fns."
  [ns]
  (require ns)
  (->> ns
       (ns-publics)
       (vals)
       (filter (comp :test meta))))

(defn- find-test-fns
  "Given namespace-syms, returns seq of test-fns.
  With no args, uses all namespaces in your project."
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
  Namespaces are symbols.
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

(defn NEW--run-tests
  "Runner and reporter are optional fns conforming to the SPEC.

   If namespaces (syms) is given, find tests only in these. Otherwise,
   look in all namespaces in project.

   If matcher is given, only run tests passing (matcher test-metadata)"
  [& {:keys [runner reporter namespaces matcher]}]
  (let [runner (or runner (default-runner))
        reporter (or reporter (default-reporter))
        test-fns (-> (if namespaces
                       (find-test-fns namespaces)
                       (find-test-fns))
                     (cond->
                      matcher (comp matcher meta)))]
    (-> test-fns
        (runner)
        (reporter))))

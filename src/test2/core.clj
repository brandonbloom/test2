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

(defn run-all-tests [& {:keys [runner reporter]}]
  (run-tests runner reporter (find-test-fns)))

(defn run-ns-tests [namespaces & {:keys [runner reporter]}]
  (run-tests runner reporter (find-test-fns namespaces)))

(defn run-matching-tests
  "Runs only tests whose metadata passes (f metadata)"
  [f & {:keys [runner reporter]}]
  (run-tests runner reporter (filter f (find-test-fns))))

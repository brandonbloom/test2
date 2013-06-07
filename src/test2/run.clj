(ns test2.run
  (:require [bultitude.core :as b]
            [test2.default.runner :refer [default-runner]]
            [test2.default.reporter :refer [default-reporter]]))

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
  If nil, uses all namespaces in your project."
  [namespaces-syms]
  (if namespaces-syms
    (mapcat test-fns-in-ns namespaces-syms)
    (find-test-fns (b/namespaces-on-classpath :classpath "src:test"))))

(defn run-tests
  "Runner and reporter are optional fns conforming to the SPEC.

  If namespaces (syms) is given, only find tests in them. Otherwise,
  find tests in all namespaces in current project.

  If matcher is given, only run tests passing (matcher test-metadata)"
  [& {:keys [runner reporter namespaces matcher]}]
  (let [runner (or runner (default-runner))
        reporter (or reporter (default-reporter))
        test-fns (-> (find-test-fns namespaces)
                     (cond->
                      matcher (comp matcher meta)))]
    (-> test-fns
        (runner)
        (reporter))))

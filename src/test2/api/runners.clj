(ns test2.api.runners
  "For writing test-runners."
  (:require [test2.api.asserters :refer [*assertions-results*]]
            [bultitude.core :as b]))

(defn run-test-fn
  "Turns a test-fn into a test-result."
  [test-fn]
  (binding [*assertions-results* (ref [])]
    (test-fn)
    {:test test-fn
     :results @*assertions-results*}))

(defn test-fns-in-ns
  "Given a ns-symbol, return a seq of test-fns."
  [ns]
  (require :reload ns)
  (->> ns
       (ns-publics)
       (vals)
       (filter (comp :test meta))))

(defn find-test-fns
  "Given seq of namespaces, returns seq of test-fns.
  If nil, uses all namespaces in your project."
  [namespaces-syms]
  (mapcat test-fns-in-ns
          (or namespaces-syms
              (b/namespaces-on-classpath :classpath "src:test:spec"))))

(defn test-fn-passes-matcher? [matcher-fn test-fn]
  (if matcher-fn
    ((comp matcher-fn meta) test-fn)
    true))

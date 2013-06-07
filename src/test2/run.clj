(ns test2.run
  "Entry point for running tests via code (i.e. in the REPL)."
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
  (mapcat test-fns-in-ns
          (or namespaces-syms
              (b/namespaces-on-classpath :classpath "src:test:spec"))))

(defn run-tests
  "Runner and reporter are optional fns conforming to the SPEC.

  With :namespaces (seq of syms), only find tests in them. Otherwise,
  find tests in all namespaces in current project.

  With :matcher, only run test-fns where (matcher (meta test-fn))"
  [& {:keys [runner reporter namespaces matcher]}]
  (let [runner (or runner default-runner)
        reporter (or reporter default-reporter)
        test-fns (find-test-fns namespaces)
        test-fns (if matcher
                   (filter (comp matcher meta) test-fns)
                   test-fns)]
    (-> test-fns
        (runner)
        (reporter))))

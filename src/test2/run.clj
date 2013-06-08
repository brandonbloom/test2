(ns test2.run
  "Entry point for running tests via code (i.e. in the REPL)."
  (:require [test2.default.runner :refer [linear-runner]]
            [test2.default.reporter :refer [plain-reporter]]))

(defn- exit-with-code [code]
  (System/exit code))

(defn run-tests
  "Runner and reporter are optional fns conforming to the SPEC.

  With :namespaces (seq of syms), only find tests in them. Otherwise,
  find tests in all namespaces in current project.

  With :matcher, only run test-fns where (matcher (meta test-fn))"
  [& {:keys [runner reporter namespaces matcher]}]
  (let [runner (or runner linear-runner)
        reporter (or reporter plain-reporter)
        pass? (runner reporter namespaces matcher)
        exit-code (if pass? 0 1)]
    (exit-with-code exit-code)))

(defn- var-for-sym
  "Given \"foo.bar/baz\", returns a var, requiring 'foo.bar first.
  Returns nil if s is nil."
  [s]
  (when s
    (-> s
        (symbol)
        (namespace)
        (symbol)
        (require))
    (-> s
        (symbol)
        (resolve))))

(defn -main
  "Entry point for running via command line."
  [& {:strs [-runner -reporter -matcher]}]
  (run-tests :runner (var-for-sym -runner)
             :reporter (var-for-sym -reporter)
             :matcher (if -matcher (load-string -matcher))))

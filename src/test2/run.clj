(ns test2.run
  "Entry point for running tests via code (i.e. in the REPL)."
  (:require [test2.default.runner :refer [linear-runner]]
            [test2.default.reporter :refer [plain-reporter]]))

(defn run-tests
  "Runner and reporter are optional fns conforming to the SPEC.

  With :namespaces (seq of syms), only find tests in them. Otherwise,
  find tests in all namespaces in current project.

  With :matcher, only run test-fns where (matcher (meta test-fn))"
  [& {:keys [runner reporter namespaces matcher]}]
  (let [runner (or runner linear-runner)
        reporter (or reporter plain-reporter)]
    (-> (runner namespaces matcher)
        (reporter))))

(defn -main
  "Entry point for running via command line."
  [& {:strs [-runner -reporter -matcher]}]
  (run-tests :runner (if -runner (-> -runner symbol resolve))
             :reporter (if -reporter (-> -reporter symbol resolve))
             :matcher (if -matcher (load-string -matcher))))

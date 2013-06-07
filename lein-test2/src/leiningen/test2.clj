(ns leiningen.test2
  (:require [leiningen.core.eval :refer [eval-in-project]]))

(defn test2 [project & [suite-name]]
  (let [suite-fn (get-in project [:test2 :suites suite-name])]
    (eval-in-project (update-in project [:dependencies]
                                conj ['test2 "0.1.0-SNAPSHOT"])
                     (run-matching-tests suite-fn
                                         :runner (get-in project [:test2 :runner])
                                         :reporter (get-in project [:test2 :reporter])))))

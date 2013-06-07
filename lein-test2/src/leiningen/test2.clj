(ns leiningen.test2
  (:require [leiningen.core.eval :refer [eval-in-project]]))

(defn test2 [project & args]
  (eval-in-project (update-in project [:dependencies]
                              conj ['test2 "0.1.0-SNAPSHOT"])
                   (run-all-tests :runner (get-in project [:test2 :runner])
                                  :reporter (get-in project [:test2 :reporter]))))

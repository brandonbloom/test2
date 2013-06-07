(ns leiningen.test2
  (:require [test2.core :refer [run-all-tests]]))

(defn test2 [project & args]
  (run-all-tests :runner (get-in project [:test2 :runner])
                 :reporter (get-in project [:test2 :reporter])))

(ns test2.expect
  "Default functions for making assertions in tests."
  (:require [clojure.string :as s]
            [test2.api.asserters :refer [add-to-report file-and-line with-exception-reporter]]))

(defmacro expect
  "Runs (apply f args) and reports on the result."
  [f & args]
  `(with-exception-reporter ~f ~args
     (let [file-pos# (file-and-line (new java.lang.Throwable) 0)
           args# [~@args]
           result# (apply ~f args#)]
       (if result#
         (add-to-report (merge file-pos#
                               {:status :pass}))
         (add-to-report (merge file-pos#
                               {:status :fail
                                :result result#
                                :fn '~f
                                :raw-args (vec '~args)
                                :args args#}))))))

(def truthy?
  "Nicer way of saying identity"
  identity)

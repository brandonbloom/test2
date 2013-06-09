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
       (add-to-report (if result#
                        {:status :pass}
                        {:status :fail
                         :result result#
                         :fn '~f
                         :raw-args (vec '~args)
                         :args args#})
                      file-pos#))))

(def truthy?
  "Nicer way of saying identity"
  identity)

(ns test2.expect
  "Default functions for making assertions in tests."
  (:require [clojure.string :as s]
            [test2.api.asserters :refer [add-to-report file-and-line]]))

(defmacro expect
  "Runs (apply f args) and reports on the result."
  [f & args]
  (let []
    `(try
       (let [file-pos# (file-and-line (new java.lang.Throwable) 0)
             args# [~@args]
             result# (apply ~f args#)]
         (if result#
           (add-to-report (merge file-pos#
                                 {:status :pass}))
           (add-to-report (merge file-pos#
                                 {:status :fail
                                  :failure-details {:result result#
                                                    :fn '~f
                                                    :raw-args (vec '~args)
                                                    :args args#}}))))
       (catch Exception e#
         (add-to-report (merge (file-and-line e# 2)
                               {:status :error
                                :exception e#}))))))

(def ^{:doc "Nicer way of saying identity"}
  truthy? identity)

(ns test2.expect
  "Default functions for making assertions in tests."
  (:require [clojure.string :as s]
            [test2.api.asserters :refer [add-to-report]]))

(defn -expect [f args results]
  ;; uhh..
  )

(defn- file-and-line ;; TODO: move this somewhere else, for everyone to use
  [exception depth]
  (let [^StackTraceElement s (nth (.getStackTrace exception) depth)]
    {:file (.getFileName s) :line (.getLineNumber s)}))

(defmacro expect
  "Runs (apply f args) and reports on the result."
  [f & args]
  (prn (file-and-line (new java.lang.Throwable) 0))
  (prn (file-and-line (new java.lang.Throwable) 1))
  (prn (file-and-line (new java.lang.Throwable) 2))
  (prn (file-and-line (new java.lang.Throwable) 3))
  (prn (meta &form))

  ;; `(prn ~(file-and-line (new java.lang.Throwable) 1))
  ;; (let [result `(~f ~@args)]
  ;;   (prn result)
  ;;   `(prn "hi")
  ;;   ;; `(do
  ;;   ;;    (prn ~f)
  ;;   ;;    (prn ~args)
  ;;   ;;    ;; (report-pass nil)
  ;;   ;;    ;; (report-fail nil
  ;;   ;;    ;;              "yes"
  ;;   ;;    ;;              8
  ;;   ;;    ;;              'foo
  ;;   ;;    ;;              ~(name f)
  ;;   ;;    ;;              '[1 2 3]
  ;;   ;;    ;;              '[1 2 3]
  ;;   ;;    ;;              )
  ;;   ;;    ;; (prn (~f ~@args))
  ;;   ;;    )
  ;;   )

  ;; ;; ...
  ;; (try
  ;;   (catch Exception e
  ;;     (report-error nil e)))
  )

(def ^{:doc "Nicer way of saying identity"}
  truthy? identity)

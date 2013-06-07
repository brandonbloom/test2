(ns test2.api.assertions)

(def ^:dynamic *assertions-results*)

(defn report-error
  "Creates an assertion-result appropriate for unexpected errors.
  Conj's the result onto *assertions-results* for you."
  [desc e]
  (dosync
   (alter *assertions-results*
          conj {:status :error
                :description desc
                :error e})))

(defn report-pass
  "Creates an assertion-result appropriate for a passed test.
  Conj's the result onto *assertions-results* for you."
  [desc]
  (dosync
   (alter *assertions-results*
          conj {:status :pass
                :description desc})))

(defn report-fail
  "Creates an assertion-result appropriate for a Failed test.
  All params are based on assertion-result in the SPEC.
  Conj's the result onto *assertions-results* for you."
  [desc file line result fn args raw-args]
  (dosync
   (alter *assertions-results*
          conj {:status :fail
                :description desc
                :file file
                :line line
                :result result
                :fn fn
                :args args
                :raw-args raw-args})))

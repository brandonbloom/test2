(ns test2.api.assertions)

(def ^:dynamic *assertions-results*)

(defn report-error
  "Creates an assertion-result appropriate for unexpected errors.
  Conj's the result onto *assertions-results* for you."
  [desc e]
  :todo)

(defn report-pass
  "Creates an assertion-result appropriate for a passed test.
  Conj's the result onto *assertions-results* for you."
  [desc]
  :todo)

(defn report-fail
  "Creates an assertion-result appropriate for a Failed test.
  All params are based on assertion-result in the SPEC.
  Conj's the result onto *assertions-results* for you."
  [desc file line result fn args raw-args]
  :todo)

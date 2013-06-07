(ns test2.api.asserters
  "For writing asserters.")

(def ^:dynamic *assertions-results*
  "A ref to a vec, to be mutated during a test.
  Conj an assertion-result, according to the SPEC.

  NOTE: Prefer to use the convenience functions instead."
  nil)

(defn report-error
  "Creates an assertion-result appropriate for unexpected errors.
  Conj's the result onto *assertions-results* for you."
  [& {:keys [desc e]}]
  (dosync
   (alter *assertions-results*
          conj {:status :error
                :description desc
                :error e})))

(defn report-pass
  "Creates an assertion-result appropriate for a passed test.
  Conj's the result onto *assertions-results* for you."
  [& {:keys [desc]}]
  (dosync
   (alter *assertions-results*
          conj {:status :pass
                :description desc})))

(defn report-fail
  "Creates an assertion-result appropriate for a Failed test.
  All params are based on assertion-result in the SPEC.
  Conj's the result onto *assertions-results* for you."
  [& {:keys [description file line result fn args raw-args] :as details}]
  (dosync
   (alter *assertions-results*
          conj (merge details
                      {:status :fail}))))

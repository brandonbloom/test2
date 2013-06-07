(ns test2.api.asserters
  "For writing asserters.")

(declare ^:dynamic *assertions-results*
  "Bound to (ref []) inside a test.
  Asserters should conj an assertion-result onto it.
  See the SPEC for details.
  NOTE: Prefer to use (add-to-report) when possible.")

(defn add-to-report
  "Adds the assertion-result to *assertions-results* for you."
  [assertion-result]
  (dosync
   (alter *assertions-results*
          conj assertion-result)))

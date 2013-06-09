(ns test2.api.asserters
  "For writing asserters.")

(def ^:dynamic *assertions-results*
  "Bound to (ref []) inside a test.
  Asserters should conj an assertion-result onto it.
  See the SPEC for details.
  NOTE: Prefer to use (add-to-report) when possible."
  nil)

(defn add-to-report
  "Adds the assertion-result to *assertions-results* for you."
  ([assertion-result]
   (dosync
    (alter *assertions-results*
           conj assertion-result)))
  ([assertion-result file-pos]
   (->> assertion-result (merge file-pos) add-to-report)))

(defn file-and-line
  "Returns {:file, :line}.
  Tip: if you don't have an exception, create one."
  [exception depth]
  (let [^StackTraceElement s (nth (.getStackTrace exception) depth)]
    {:file (.getFileName s) :line (.getLineNumber s)}))

(defmacro with-exception-reporter
  "Reports any unexpected exceptions that occur within body.
  Useful when writing asserters. f and args are raw forms."
  [f args & body]
  `(try
     ~@body
     (catch Exception e#
       (add-to-report {:status :error
                       :fn '~f
                       :raw-args (vec '~args)
                       :exception e#}
                      (file-and-line e# 2)))))

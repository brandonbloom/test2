(ns test2.default.reporter
  "Some built-in test-reporters.")

(defn exit-with-code [code]
  (System/exit code))

(defn test->assertions [test-result]
  (let [t (:test test-result)]
    (for [assertion-result (:results test-result)]
      (assoc assertion-result :test t))))

(defn default-reporter
  "Prints only failures. Doesn't colorize the text."
  [test-results]
  (let [assertion-results (mapcat test->assertions test-results)
        groups (group-by :status assertion-results)
        failures (:fail groups)
        errors (:error groups)
        problem? (and (not (empty? failures))
                      (not (empty? errors)))]
    (doseq [failure failures]
      (println (format "In %s at line %s"
                       (:file failure)
                       (:line failure)))
      (println (format "    FAIL: (%s %s)"
                       (:fn failure)
                       (apply str (:raw-args failure))))
      (println (format "Expected: (%s %s)"
                       (:fn failure)
                       (apply str (:args failure))))
      (println (format "     Got: %s"
                       (:result failure))))
    (doseq [error errors]
      (println (format "In %s at line %s"
                       (:file error)
                       (:line error)))
      (println (format "   ERROR: (%s %s)"
                       (:fn error)
                       (apply str (:raw-args error))))
      (println (format "     Got: %s"
                       (:exception error))))
    (if problem?
      (println "\nTEST FAILED\n"))
    (println (format "Ran %s tests containing %s assertions.\n%s failures, %s errors."
                     (count test-results)
                     (count assertion-results)
                     (count (:fail groups))
                     (count (:error groups))))
    (exit-with-code (if problem? 1 0))))

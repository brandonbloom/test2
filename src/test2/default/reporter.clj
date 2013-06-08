(ns test2.default.reporter
  "Some built-in test-reporters.")

(defn exit-with-code [code]
  (System/exit code))

(defn test->results [test-result]
  (let [t (:test test-result)]
    (for [assertion-result (:results test-result)
          :when (= :fail (:status assertion-result))]
      (assoc assertion-result :test t))))

(defn default-reporter
  "Prints only failures. Doesn't colorize the text."
  [test-results]
  (let [assertion-results (mapcat :results test-results)
        groups (group-by :status assertion-results)
        failed? (not (empty? (:fail groups)))
        failures (mapcat test->results test-results)]
    (doseq [failure failures]
      (let [details (:failure-details failure)]
        (println (format "In %s at line %s"
                         (:file failure)
                         (:line failure)))
        (println (format "    FAIL: (%s %s)"
                         (:fn details)
                         (apply str (:raw-args details))))
        (println (format "Expected: (%s %s)"
                         (:fn details)
                         (apply str (:args details))))
        (println (format "     Got: %s"
                         (:result details)))))
    (if failed?
      (println "\nTEST FAILED\n"))
    (println (format "Ran %s tests containing %s assertions.\n%s failures, %s errors."
                     (count test-results)
                     (count assertion-results)
                     (count (:fail groups))
                     (count (:error groups))))
    (exit-with-code (if failed? 1 0))))

(ns test2.default.reporter
  "Some built-in test-reporters.")

(defn exit-with-code [code]
  (System/exit code))

(defn default-reporter
  "Prints only failures. Doesn't colorize the text."
  [test-results]
  (let [assertion-results (mapcat :results test-results)
        groups (group-by :status assertion-results)
        failed? (not (empty? (:fail groups)))]
    (if failed?
      (println "FAIL\n"))
    (println (format "Ran %s tests containing %s assertions.\n%s failures, %s errors."
                     (count test-results)
                     (count assertion-results)
                     (count (:fail groups))
                     (count (:error groups))))
    (exit-with-code (if failed? 1 0))))

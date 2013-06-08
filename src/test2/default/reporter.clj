(ns test2.default.reporter
  "Some built-in test-reporters.")

(defn exit-with-code [code]
  (System/exit code))

(defn default-reporter
  "Prints only failures. Doesn't colorize the text."
  [test-results]
  (let [failed? (not (empty? (:fail (group-by :status (mapcat :results test-results)))))]
    (if failed?
      (println "FAIL\n"))
    (println (format "Ran %s tests containing %s assertions.\n%s failures, %s errors."
                     1
                     2
                     3
                     4
                     ))
    (exit-with-code (if failed? 1 0)))



  ;; (let [failures (group-by :status test-results)]
  ;;   (doseq [failure failures]
  ;;     (println (format "FAIL in %s at line %s\n"
  ;;                      (:file failure)
  ;;                      (:line failure))
  ;;              (format " Expected: %s"
  ;;                      (:file failure)
  ;;                      (:line failure)))

  ;;     )

  ;;   )
  )

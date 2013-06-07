(ns test2.default.reporter
  "Some built-in test-reporters.")

(defn default-reporter
  "Prints only failures. Doesn't colorize the text."
  [test-results]
  (prn test-results)
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

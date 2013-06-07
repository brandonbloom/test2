(ns test2.default.reporter
  "Some built-in test-reporters.")

(defn default-reporter
  "Prints only failures. Doesn't colorize the text."
  [test-results]
  (if (not (empty? (:fail (group-by :status (mapcat :results test-results)))))
    (println "FAIL"))

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

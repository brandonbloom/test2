(ns test2.default.reporter
  "Some built-in test-reporters.")

(defn- exit-with-code [code]
  (System/exit code))

(defn- test->assertions [test-result]
  (let [t (:test test-result)]
    (for [assertion-result (:results test-result)]
      (assoc assertion-result :test t))))

(defn- default-reporter
  "Prints failures/errors and summary. Optionally colorizes the text."
  [test-results colorize?]
  (let [red-color    (if colorize? (str (char 27) "[31m;") "")
        green-color  (if colorize? (str (char 27) "[32m;") "")
        yellow-color (if colorize? (str (char 27) "[33m;") "")
        reset-color  (if colorize? (str (char 27) "[0m;")  "")
        assertion-results (mapcat test->assertions test-results)
        groups (group-by :status assertion-results)
        failures (:fail groups)
        errors (:error groups)
        problem? (or (not (empty? failures))
                     (not (empty? errors)))]
    (doseq [failure failures]
      (println (format "In %s at line %s" (:file failure) (:line failure)))
      (println (format "    %sFAIL%s: (%s %s)" yellow-color (:fn failure) (apply str (interpose " " (:raw-args failure))) reset-color))
      (println (format "Expected: (%s %s)" (:fn failure) (apply str (interpose " " (:args failure)))))
      (println (format "     Got: %s" (:result failure)))
      (println))
    (doseq [error errors]
      (println (format "In %s at line %s" (:file error) (:line error)))
      (println (format "   %sERROR%s: (%s %s)" red-color (:fn error) (apply str (interpose " " (:raw-args error))) reset-color))
      (println (format "     Got: %s" (:exception error)))
      (println))
    (if problem?
      (println (format "%sTEST FAILED%s\n" red-color reset-color))
      (println (format "%sTEST PASSED%s\n" green-color reset-color)))
    (println (format "%sRan %s tests containing %s assertions.\n%s failures, %s errors.%s"
                     (if problem? red-color green-color)
                     (count test-results)
                     (count assertion-results)
                     (count (:fail groups))
                     (count (:error groups))
                     reset-color))
    (exit-with-code (if problem? 1 0))))

(defn plain-reporter
  "Prints failures/errors and summary. Doesn't colorize the text."
  [test-results]
  (default-reporter test-results false))

(defn colorized-reporter
  "Prints failures/errors and summary. Colorizes text for terminals."
  [test-results]
  (default-reporter test-results true))

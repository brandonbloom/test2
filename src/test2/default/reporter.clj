(ns test2.default.reporter
  "The default test-reporter used if none is specified.")

(defn default-reporter
  "Prints only failures. Doesn't colorize the text."
  [test-results]
  nil
  )

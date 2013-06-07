(ns test2.default.reporter
  "Some built-in test-reporters.")

(defn default-reporter
  "Prints only failures. Doesn't colorize the text."
  [test-results]
  nil
  )

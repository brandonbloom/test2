(ns test2.default.reporter
  "Some built-in test-reporters.")

(defn exit-with-code [code]
  (System/exit code))

(defn test->results [test-result]
  (let [t (:test test-result)]
    (for [assertion-result (:results test-result)]
      (assoc assertion-result :test t))))

(defn default-reporter
  "Prints only failures. Doesn't colorize the text."
  [test-results]
  (let [assertion-results (mapcat :results test-results)
        groups (group-by :status assertion-results)
        failed? (not (empty? (:fail groups)))
        failures (mapcat test->results test-results)]
    (doseq [failure failures]

      (println "CRAP"))
    (if failed?
      (println "\nFAIL\n"))
    (println (format "Ran %s tests containing %s assertions.\n%s failures, %s errors."
                     (count test-results)
                     (count assertion-results)
                     (count (:fail groups))
                     (count (:error groups))))
    (exit-with-code (if failed? 1 0))))


;; (def data-in
;;   [{:name "Bob"
;;     :cards [{:id 1}
;;             {:id 2}]}
;;    {:name "Jack"
;;     :cards [{:id 3}
;;             {:id 4}]}])

;; (def data-out
;;   [{:id 1 :name "Bob"}
;;    {:id 2 :name "Bob"}
;;    {:id 3 :name "Jack"}
;;    {:id 4 :name "Jack"}])

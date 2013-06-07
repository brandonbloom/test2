(ns test2.finder)

(defn- all-namespaces-in-project []
  )

(defn- find-test-fns
  "Returns seq of test-fns within your project."
  ([in-namespaces]
     nil)
  ([]
     (find-test-fns (all-namespaces-in-project))))

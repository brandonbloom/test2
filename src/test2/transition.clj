(ns test2.transition
  "Helpers to transition from clojure.test")

(defmacro deftest
  "Creates a test using clojure.test/deftest syntax"
  [name & body]
  )

(defmacro use-fixtures
  "Combines tests using clojure.test/use-fixtures syntax"
  [& args]
  )

(defmacro is
  "Makes assertions using clojure.test/is syntax"
  [& args]
  )

(defmacro testing
  "Combines tests using clojure.test/testing syntax"
  [& args]
  )

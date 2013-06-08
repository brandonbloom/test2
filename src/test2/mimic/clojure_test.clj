(ns test2.mimic.clojure-test
  "Helpers to transition from clojure.test"
  (:require [test2.expect :as ex]))

(defmacro deftest
  "Creates a test using clojure.test/deftest syntax"
  [name & body]
  `(defn ~(with-meta name {:test true}) []
     ~@body))

(defmacro is
  "Makes assertions using clojure.test/is syntax"
  [[f & args]]
  `(ex/expect ~f ~@args))

(defmacro testing
  "Combines tests using clojure.test/testing syntax"
  [string & body]
  `(do ~@body))

;; (defmacro use-fixtures
;;   "Combines tests using clojure.test/use-fixtures syntax"
;;   [& args]
;;   )

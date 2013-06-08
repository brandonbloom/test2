(ns test2.mimic.ct-test-data
  (:require [test2.mimic.clojure-test :as ct]))

(ct/deftest t1
  (print "t1 "))

(ct/deftest t2
  (print "t2 "))

(defn f1 []
  (print "f1 "))

(defn f2 []
  (print "f2 "))

(ct/use-fixtures :once f1 f2)

(defn test-ns-hook [])

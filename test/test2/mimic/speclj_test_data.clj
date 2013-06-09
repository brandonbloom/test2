(ns test2.mimic.speclj-test-data
  (:require [test2.mimic.speclj :as s]))

(s/describe "something"

  (s/it "is really cool"
    (print "foo"))

  (s/it "is really neat"
    (print "bar"))

  )

(defn test-ns-hook [])

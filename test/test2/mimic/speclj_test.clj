(ns test2.mimic.speclj-test
  (:require [clojure.test :as ct]
            [test2.api.runners :as r]
            [test2.mimic.speclj-test-data]))

(ct/deftest speclj-basics
  (let [s (with-out-str
            (doseq [f (r/test-fns-in-ns 'test2.mimic.speclj-test-data)]
              (f)))]
    (ct/is (= "foobar" s))))

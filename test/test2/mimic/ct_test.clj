(ns test2.mimic.ct-test
  (:require [clojure.test :as ct]
            [test2.mimic.ct-test-data]))

;; (ct/deftest once-fixtures
;;   (ct/is (= "f1 t1 t2 f1"
;;             (with-out-str
;;               (test2.mimic.ct-test-data/t1)
;;               (test2.mimic.ct-test-data/t2)))))

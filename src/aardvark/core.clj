(ns aardvark.core
  (:require [aardvark.prototype :refer [dictionary]]
            [clojure.set :refer [difference]]))


;; parse: how to deserialize translations
;; code-gen: what macros will output
;; serialize: how will translations be stored?
(defprotocol ICompile
  (parse [this])
  (code-gen [this])
  (serialize [this]))

(defmacro tr [& args])

(defmacro locale [& args])

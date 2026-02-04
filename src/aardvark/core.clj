(ns aardvark.core
  (:require [aardvark.prototype :refer [translations]]
            [clojure.set :refer [difference]]))

(def tr-instances (atom {}))

(defmacro tr-lang [lang]
  `#(reset! aardvark.core/lang ~lang))

(defmacro tr [phrase]
  (let [translation (phrase translations)
        id (keyword (gensym phrase))]
    (swap! tr-instances assoc phrase (meta &form))
    `[:span {:ref #(swap! aardvark.core/translate-instances assoc ~id {:el % :translation ~translation})}
      (@aardvark.core/lang ~translation)]))

(defmacro tr-prep []
  `(aardvark.core/watch-lang))

(defmacro tr-logs []
  (println @tr-instances)
  (println (set (keys @tr-instances)))
  (let [used-phrases (set (keys @tr-instances))
        unused-phrases (difference (set (keys translations)) used-phrases)]
    (println used-phrases)
    (println unused-phrases)
    ))

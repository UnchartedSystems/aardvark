(ns aardvark.core
  (:require [aardvark.prototype :refer [translations]]
            [clojure.set :refer [difference]]))

(def tr-instances (atom {}))
(def lang-instances (atom {}))

(defmacro lang [lang]
  (swap! lang-instances assoc lang (meta &form))
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
  (let [used-langs (set (keys @lang-instances))
        all-langs (reduce #(into %1 (keys %2)) #{} (vals translations))
        unused-langs (difference all-langs used-langs)
        dead-langs (difference used-langs all-langs)
        used-phrases (set (keys @tr-instances))
        all-phrases (set (keys translations))
        unused-phrases (difference all-phrases used-phrases)
        dead-phrases (difference used-phrases all-phrases)]
    
    (println "Langs")
    (println "unused: " unused-langs)
    (println "dead:   " dead-langs)

    (println "Phrases")
    (println "unused: " unused-phrases)
    (println "dead:   " dead-phrases)))


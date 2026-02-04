(ns aardvark.core)

(def translations
  {:hello {:en "Hello"
           :fr "Bonjour"}
   :thanks {:en "Thanks"
            :fr "Merci"}})

(defmacro change-lang [lang]
  `#(reset! aardvark.core/lang ~lang))

(defmacro translate [phrase]
  (let [phrases (phrase translations)
        id (keyword (gensym phrase))]
    `[:span {:ref #(swap! aardvark.core/translate-instances assoc ~id {:el % :phrases ~phrases})}
      (@aardvark.core/lang ~phrases)]))

(defmacro prepare []
  `(aardvark.core/watch-lang))

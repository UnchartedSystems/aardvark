(ns aardvark.core)

(def translations
  {:hello {:en "Hello"
           :fr "Bonjour"}
   :thanks {:en "Thanks"
            :fr "Merci"}})

(defmacro translate [phrase]
  (let [phrases (phrase translations)]
    `[:span (@aardvark.core/lang ~phrases)]))

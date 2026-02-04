(ns aardvark.parsing)

;;;; Example Formats

#_{:greeting {:en "Hello"
            :fr "Bonjour"}
 :farewell {:en "Goodbye"
            :fr "Au revoir"}}

#_{:en {:greeting "Hello"
      :farewell "Goodbye"}
 :fr {:greeting "Bonjour"
      :farewell "Au revoir"}}

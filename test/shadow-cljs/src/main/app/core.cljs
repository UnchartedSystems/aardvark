(ns app.core
  (:require [reagent.dom.client :as rdom]
            [aardvark.core :refer-macros [translate change-lang prepare]]))

(defn app []
  [:div
   [:h1 "Aardvark i18n Test"]
   [:h1 (translate :hello)]
   [:p (translate :thanks)]
   [:button {:on-click (change-lang :en)} "English"]
   [:button {:on-click (change-lang :fr)} "French"]
   [:p "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."]
   [:p "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."]])


(defonce root (atom nil))

(defn init []
  (prepare)
  (let [container (js/document.getElementById "app")]
    (reset! root (rdom/create-root container))
    (rdom/render @root [app])))


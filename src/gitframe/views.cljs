(ns gitframe.views
  (:require
    [re-frame.core :as re-frame]
    [gitframe.api.github.subs]
    [gitframe.api.github.events]
    [reagent.core :as rcore]))

;; TODO: Isolate components
;; TODO: Make things look better (tailwind css)

(defonce github-user (rcore/atom ""))

(defn main-panel []
  (let [repos @(re-frame/subscribe [:github.user/repos])]
    [:div
     [:h1
      "Gitframe"]
     [:form {:on-submit (fn [e]
                          (.preventDefault e)
                          (re-frame/dispatch [:github.user/get-repos @github-user]))}
      [:input {:type      "text"
               :value     @github-user
               :on-change (fn [e]
                            (reset! github-user (.-value (.-target e))))}]
      [:button {:type "submit"} "Procurar"]]
     (if (not (empty? repos))
       (->> repos
            (mapv (fn [repo]
                    [:p (:name repo)])
                  repos)
            (into [:div]))
       [:p "nenhum repositório"])]))

(comment
  (->> (re-frame/subscribe [:github.user/repos])
       deref
       (mapv (fn [repo]
               [:p (:language repo)])
             ))

  )
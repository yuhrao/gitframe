(ns gitframe.api.github.events
  (:require [re-frame.core :as re-frame]
            [ajax.core :as ajax]))

(def base-url "https://api.github.com")


;; List user's repo
;; TODO: Handle errors
;; TODO: Handle pagination
(re-frame/reg-event-db
  :github.user/set-name-success
  (fn [db [_ result]]
    (assoc-in db [:github :repos] result)))

(re-frame/reg-event-fx
  :github.user/get-repos
  (fn [_ [_ user-name]]
    {:http-xhrio {:method :get
                  :uri (str base-url "/users/" user-name "/repos")
                  :response-format (ajax/json-response-format {:keywords? true})
                  :on-success [:github.user/set-name-success]
                  :on-failure []}}))

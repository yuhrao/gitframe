(ns gitframe.events
  (:require
   [re-frame.core :as re-frame]
   [gitframe.db :as db]
   ))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))

(re-frame/reg-event-db
  :user/set-name
  (fn [db [_ name]]
    (println "setting name")
    (assoc db :name name)))
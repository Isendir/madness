(ns madness.blog.archive
  (:require [net.cgrand.enlive-html :as h]
            [madness.blog.post :as blog-post]
            [madness.blog :as blog]
            [madness.blog.nav :as blog-nav]
            [madness.utils :as utils]
            [madness.config :as cfg]
            [madness.blog.recent :as blog-recent]
            [clojure.string :as str]))

(h/defsnippet archive-post-item (cfg/template) [:.recent-post]
  [post]

  [:.recent-post] (h/remove-class "recent-post")
  [:h2] (h/add-class "archived")
  [:h2 :a] (utils/rewrite-link-with-title
             (:url post) (:title post))
  [:.post-footer] nil
  [:p.summary] nil)

(h/defsnippet archive-post-row (cfg/template) [:#recents]
  [posts]

  [:#recents] (h/remove-attr :id)
  [:#recent-posts :.recent-post]
    (h/clone-for [p posts]
                 (h/do->
                  (h/substitute (archive-post-item p))
                  (h/set-attr :class (str "span" (cfg/archive-posts :span)))))
  [:#recent-posts] (h/do->
                    (h/remove-attr :id)
                    (h/remove-class "visible-desktop")))

(h/deftemplate blog-archive (cfg/template)
  [title blog-posts all-posts]

  [:.hero-unit :h1] (h/do->
                     (h/content title)
                     (h/set-attr :title title))
  [:.hero-unit :p] nil
  [:#hero-full] nil
  [:#full-article-footer] nil
  [:#post-neighbours] nil
  [:#disqus] nil
  [:#recents] (h/clone-for [recent (utils/blog->table
                                    (cfg/recent-posts :columns)
                                    (cfg/recent-posts :rows) blog-posts)]
                           (h/do->
                            (h/substitute (blog-recent/recent-post-row recent true))
                            (h/before [{:tag :hr}])))
  [:#archive] (h/clone-for [archive (utils/blog->table
                                     (cfg/archive-posts :columns)
                                     (cfg/archive-posts :rows)
                                     (drop (dec (cfg/recent-posts :total)) blog-posts))]
                           (h/do->
                            (h/substitute (archive-post-row archive))
                            (h/before utils/hr-desktop)))
  [:#nav-recent-posts :ul :li] (blog-nav/recent-posts all-posts)
  [:#nav-tags :ul :li] (blog-nav/all-tags all-posts))

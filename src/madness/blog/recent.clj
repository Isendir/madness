(ns madness.blog.recent
  "## Recent posts on archive & index pages

  The archive pages - along with the site index - will display a
  number of recent and (in case of archive pages) archived posts. The
  snippets herein lay the foundation for rendering those on all these
  pages.

  One important thing to note, is that recent and archived posts are
  not displayed on non-desktop resolutions, unless we're rendering an
  archive page. That is, on a smartphone, the index page will not have
  any recent posts shown."

  ^{:author "Gergely Nagy <algernon@madhouse-project.org>"
    :copyright "Copyright (C) 2012 Gergely Nagy <algernon@madhouse-project.org>"
    :license {:name "GNU General Public License - v3"
              :url "http://www.gnu.org/licenses/gpl.txt"}}
  
  (:require [net.cgrand.enlive-html :as h]
            [madness.utils :as utils]
            [madness.config :as cfg]))

;; The first nippet to care about is the tag list of a recent post
;; item. We use the element with a `tag` class under the
;; `#recent-posts` element of the template as a basis for the snippet.
;;
;; We replace the `href` (as it should be an anchor), and pick out a
;; `&lt;span>` element from under it, and replace that with the tag's
;; name. The reason we use a separate element for the tag name, is
;; because we want to allow the template to also add an icon somewhere
;; onto the tag button.
;;
;; Madness does not enforce the icon, but makes it possible to use one.
;;
;; This snippet is for a single tag, it will need to be cloned for all
;; tags, see later!
(h/defsnippet recent-post-tag (cfg/template) [:#recent-posts :.tag]
  [tag]

  [:a] (h/do->
        (h/remove-class "tag")
        (h/set-attr :href (utils/tag-to-url tag))
        (h/after " "))
  [:a :span] (h/substitute tag))

;; We'll use the element with a `recent-post` class as the basis for a
;; single recent post item. This should have a header (&lt;h2>), and
;; an link under it - the anchor's `href` and textual content will be
;; rewritten appropriately.
;;
;; The same `recent-post` element must also have a child with a
;; `post-date` class, whose content will be substituted by the date
;; the blog post was made upon.
;;
;; Then there is the paragraph, with a class of `summary`, which will
;; be replaced by the blog post's summary, and of course the `tag`
;; classed element will be replaced by the list of tags
;; (`recent-post-tag` above cloned for all tags).
;;
(h/defsnippet recent-post-item (cfg/template) [:.recent-post]
  [post]

  [:.recent-post] (h/remove-class "recent-post")
  [:h2 :a] (utils/rewrite-link-with-title
             (:url post)
             (:title post))
  [:.post-date] (h/substitute (utils/date-format (:date post)))
  [:.tag] (h/clone-for [tag (:tags post)]
                       (h/substitute (recent-post-tag tag)))
  [:p.summary] (h/do->
                (h/remove-attr :class)
                (h/substitute (:summary post))))

;; And finally, we are now able to assemble a whole row of recent
;; posts! We'll use the `#recents` element as a basis for our
;; template.
;;
;; Since there can be multiple rows, we'll remove the id, and for each
;; item on the row, we'll clone the `recent-post-item` snippet,
;; replacing the `.recent-post` element in the template.
;;
;; If we're rendering an archive, we'll also remove the
;; `visible-desktop` class, since archives should be visible on
;; non-desktop resolutions too.
(h/defsnippet recent-post-row (cfg/template) [:#recents]
  [posts archive?]

  [:#recents] (h/remove-attr :id)
  [:#recent-posts :.recent-post]
    (h/clone-for [p posts]
                 (h/do->
                  (h/substitute (recent-post-item p))
                  (h/set-attr :class (str "span" (cfg/recent-posts :span)))))
  [:#recent-posts] (h/do->
                    (h/remove-attr :id)
                    (if archive?
                      (h/remove-class "visible-desktop")
                      identity)))

(defproject rum "0.12.8"
  :description "ClojureScript wrapper for React"
  :license {:name "Eclipse"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :url "https://github.com/tonsky/rum"

  :dependencies
  [[org.clojure/clojure "1.9.0" :scope "provided"]
   [org.clojure/clojurescript "1.10.773" :scope "provided"]
   [cljsjs/react "16.8.6-0"]
   [cljsjs/react-dom "16.8.6-0"]]

  :plugins [[lein-cljsbuild "1.1.7"]
            [lein-cljfmt "0.6.7"]
            [lein-figwheel "0.5.20" :exclusions [org.clojure/clojure]]]

  :profiles {:dev {:source-paths ["src" "dev" "examples"]
                   :dependencies [[cljsjs/react-dom-server "16.8.6-0"]
                                  [cljsjs/prop-types "15.7.2-0"]
                                  [clj-diffmatchpatch "0.0.9.3" :exclusions [org.clojure/clojure]]
                                  [com.cemerick/piggieback "0.2.2"]
                                  [org.clojure/tools.nrepl "0.2.12"]
                                  [binaryage/devtools "1.0.0"]
                                  [figwheel-sidecar "0.5.20"]
                                  [expound "0.7.1"]
                                  [cider/piggieback "0.5.3"]
                                  [org.clojure/tools.nrepl  "0.2.13"]]}
             :repl-options {:nrepl-middleware [cider.piggieback/wrap-cljs-repl]}
             :perf {:source-paths ["perf"]
                    :dependencies
                    [[enlive "1.1.6"]
                     [criterium "0.4.4"]
                     [hiccup "1.0.5"]]}}

  :aliases {"package" ["do" ["clean"] ["test"] ["clean"] ["cljsbuild" "once" "advanced"] ["run" "-m" "rum.examples-page"]]
            "perf" ["with-profile" "perf" "run" "-m" "rum.perf"]}


  :cljsbuild
  {:builds
   [{:id "advanced"
     :source-paths ["src" "examples" "test"]
     :compiler
     {:main rum.examples
      :output-to "target/main.js"
      :optimizations :advanced
      :source-map "target/main.js.map"
      :pretty-print false
      :compiler-stats true
      :parallel-build true}}

    {:id "none"
     :source-paths ["src" "examples" "test"]
     :figwheel {
                ;; :on-jsload "hello-world.core/on-js-reload"
                ;; :open-urls will pop open your application
                ;; in the default browser once Figwheel has
                ;; started and compiled your application.
                ;; Comment this out once it no longer serves you.
                :open-urls ["http://localhost:3449/index.html"]}
     :compiler
     {:main rum.examples
      :asset-path "js/compiled/out"
      :output-to "resources/public/js/compiled/rum.js"
      :output-dir "resources/public/js/compiled/out"
      :source-map-timestamp true
      ;; To console.log CLJS data-structures make sure you enable devtools in Chrome
      ;; https://github.com/binaryage/cljs-devtools
      :preloads [devtools.preload]
      :optimizations :none
      :source-map true
      :compiler-stats true
      :parallel-build true}}

    {:id "test"
     :source-paths ["src" "test"]
     :compiler
     {:main rum.test.server-render
      :output-to "target/test.js"
      :output-dir "target/test"
      :asset-path "target/test"
      :optimizations :advanced
      :pretty-print true
      :pseudo-names true
      :parallel-build true}}]}

  :figwheel {;; :http-server-root "public" ;; default and assumes "resources"
             ;; :server-port 3449 ;; default
             ;; :server-ip "127.0.0.1"

             ;; :css-dirs ["resources/public/css"] ;; watch and update CSS

             ;; Start an nREPL server into the running figwheel process
             :nrepl-port 7888

             :nrepl-middleware ["cider.nrepl/cider-middleware"
                                ;; "refactor-nrepl.middleware/wrap-refactor"
                                "cemerick.piggieback/wrap-cljs-repl"]
             ;; Server Ring Handler (optional)
             ;; if you want to embed a ring handler into the figwheel http-kit
             ;; server, this is for simple ring servers, if this

             ;; doesn't work for you just run your own server :) (see lein-ring)

             ;; :ring-handler hello_world.server/handler

             ;; To be able to open files in your editor from the heads up display
             ;; you will need to put a script on your path.
             ;; that script will have to take a file path and a line number
             ;; ie. in  ~/bin/myfile-opener
             ;; #! /bin/sh
             ;; emacsclient -n +$2 $1
             ;;
             ;; :open-file-command "myfile-opener"

             ;; if you are using emacsclient you can just use
             ;; :open-file-command "emacsclient"

             ;; if you want to disable the REPL
             ;; :repl false

             ;; to configure a different figwheel logfile path
             ;; :server-logfile "tmp/logs/figwheel-logfile.log"

             ;; to pipe all the output to the repl
             ;; :server-logfile false
             }

  )

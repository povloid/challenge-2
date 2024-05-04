(defproject challenge "0.1.0"
  :description "The Clojure challenge."
  :url "https://clojure.org"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.11.3"]
                 [org.clojure/clojurescript "1.11.132"]
                 [cheshire "5.13.0"]
                 [compojure "1.7.1"]
                 [com.taoensso/timbre "6.5.0"]
                 [ring/ring-json "0.5.1"]
                 [cljsjs/react "17.0.2-0"]
                 [cljsjs/react-dom "17.0.2-0"]
                 [reagent "1.2.0"]
                 [re-frame "1.4.3"]
                 [cljs-ajax "0.8.4"]
                 [clj-http "3.12.3"]]

  :jvm-opts ^:replace ["-Dfile.encoding=UTF-8"]

  :plugins [[lein-ring "0.12.6"]
            [lein-cljsbuild "1.1.8" :exclusions [[org.clojure/clojure]]]
            [lein-figwheel "0.5.20"]]



  :auto-clean false
  :clean-targets ^{:protect false} ["resources/public/js"
                                    "target"]

  :resource-paths ["resources"]

  :source-paths ["src/cljc"
                 "src/clj"
                 "src/cljs"]

  :ring {:handler       challenge.handler/app
         :auto-reload?  false
         :auto-refresh? false
         :uberwar-name  "challenge.war"
         :port          3000}

  :figwheel {:ring-handler challenge.handler/app
             :nrepl-port   7888}

  :profiles {:dev {:dependencies [[binaryage/devtools "1.0.0"]
                                  [figwheel-sidecar "0.5.20"]]}

             :uberwar {:aot         :all
                       :omit-source true}
             :war     {:aot         :all
                       :omit-source true}
             :uberjar {:aot         :all
                       :omit-source true}
             :jar     {:aot         :all
                       :omit-source true}}

  :cljsbuild
  {:builds
   [{:id           "dev"
     :source-paths ["src/cljs"]
     :figwheel     {:on-jsload "challenge.core/mount-root"}
     :compiler     {:output-to      "resources/public/js/app.js"
                    :output-dir     "resources/public/js/out"
                    :asset-path     "js/out"
                    :main           challenge.core
                    :optimizations  :none
                    :source-map     true
                    :cache-analysis true}}
    {:id           "prod"
     :source-paths ["src/cljs"]
     :compiler     {:output-to      "resources/public/js/app.js"
                    :main           challenge.core
                    :optimizations  :advanced
                    :cache-analysis true}}]})

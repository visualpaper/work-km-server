# work-km-server-public

* Maven
* Java 21
    - lombok
    - guava

* Spring Boot 3.2.5
    - spring-boot-starter-web
    - spring-boot-starter-tomcat
    - spring-boot-starter-test  
      ※ Sprint バージョンと Java バージョンとの不整合で Compile が通らないケースもあるため注意が必要。  
      ※ java.version の指定を行っても、Project Setting などで JDK バージョンを変更してもダメなら↑を疑うべき。

## setup

### project

* [spring-initializer](https://start.spring.io/)
    - 設定  
      ※ GroupId: org.visualpaper.work  
      ※ Artifact: km-server  
      ※ Package Name: org.visualpaper.work.km-server  
      ※ Packaging: War  
      ※ Java: 21
    - src と .gitignore と pom.xml をコピー
    - resource 配下の static/templates 削除
    - war を選択した場合 ServletInitializer が必要になる  
      ※ Tomcat で稼働する場合、上記の configure が呼ばれることで稼働する。  
      ※ 個人でやるなら Jar で良いが、性能調整や Tomcat 監視を考えると War のほうが良いと考えている。

    - モジュール分離・pom 整備  
      ※ api/impl などモジュールを作成  
      ※ 前の実装物などを元に pom 整理を行う  
      ※ なるべくバージョンは最新を利用すること

<br><br>

### intelij

* [Google Java Style](https://github.com/google/styleguide/blob/gh-pages/intellij-java-google-style.xml)　　
  - Editor -> Code Style -> Java -> Import Scheme で取り込む

* [lombok Plugin](https://plugins.jetbrains.com/plugin/6317-lombok)
    - Plugin -> Lombok で検索し上記 Plugin を取り込む

* その他設定
    - Project -> Tool Appearance  
      ※ Flatten Packages を off  
      ※ Compact Middle Packages を off  
      ※ パッケージをまとめないように設定している。

    - 行番号が出ている場所を右クリック  
      ※ Show Indent guide を off  
      ※ コード上の左に出る blame 情報を表示しないようにしている。

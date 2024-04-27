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

<br><br>

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

<br>

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

<br>

### env

* application
    - 本番環境用

* application-dev
    - ローカル環境用  
      ※ Plugins -> spring-boot -> spring-boot:run 右クリックで Modify Run Configuration し以下を設定すること。  
      ※ 設定後、Intelij のツールバーから Run -> Run or Debug で実行することで有効化される点に注意すること。  
      ※ 設定しない場合、デバッグ実行しても終了できない (8080 port をつかんだままになる) ので複数回デバッグ実行できることを確認すること。  
      ※ Spring 3 系からさらに変化した (https://docs.spring.io/spring-boot/docs/3.0.x/maven-plugin/reference/htmlsingle/#run.examples.debug)  
      > spring-boot:run -Dspring-boot.run.fork=false -Dspring-boot.run.jvmArguments="-Dspring.profiles.active=dev -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=8080" -f pom.xml

* application-test
    - テスト用  
      ※ `@ActiveProfiles("test")` をテストクラスに付けることで利用可能

<br><br>

## library

### open api

* [openapi-generator](https://github.com/OpenAPITools/openapi-generator/blob/master/modules/openapi-generator-maven-plugin/README.md)
  - [swagger editor](https://editor.swagger.io/)  
    ※ impl pom へ compile scope で dependency し利用する。  
    ※ 依存関係は [plugin の pom](https://github.com/OpenAPITools/openapi-generator/blob/v6.2.1/modules/openapi-generator-maven-plugin/examples/spring.xml) を参考にしている。

<br>

### logback

* logback-spring.xml を用意し `LOG_FILE_BASE` および `LOG_PATH` を設定する。  
  ※ `LOG_PATH` は application.property ファイル上でも指定する。

* github Actions 上で `LOG_PATH` 配下を操作可能なように記述を追加する。

```
        run: |
          sudo mkdir -p /vis/km
          sudo chown -R runner:docker /vis/km
          sudo chmod -R 777 /vis/km
          mvn clean install
```

* logback
    - App Log  
      ※ WARN 以上のログを出力する。  
      ※ StackTrace は出ない。  
      ※ 検知ログとして利用する。

    - Dump Log  
      ※ INFO 以上のログを出力する。  
      ※ StackTrace は出る。  
      ※ 調査ログとして利用する。

    - コンソール  
      ※ INFO 以上のログを出力する。  
      ※ StackTrace は出る。  
      ※ 開発時用のログとして利用する。

<br>

### unit test

* Junit5
    - junit-jupiter-engine
    - junit-jupiter-params
    - junit-jupiter-api
    - [assertj](https://joel-costigliola.github.io/assertj/)  
      ※ アサーションに利用

<br>

## CI

Github Actions を利用

```
name: CI
on:
  pull_request:
    branches: [main]

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - name: Set up JDK 21
        uses: actions/setup-java@v1
        with:
          java-version: '21'
      - name: CI
        working-directory: ./
        run: |
          sudo mkdir -p /vis/km
          sudo chown -R runner:docker /vis/km
          sudo chmod -R 777 /vis/km
          mvn clean install
        env:
          CI: false
```

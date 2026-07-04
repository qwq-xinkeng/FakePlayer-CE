name: Build FakePlayer-CE (Fixed)

on:
  push:
    branches: [ "master", "main" ]
  workflow_dispatch: 

jobs:
  build:
    runs-on: ubuntu-latest

    steps:
    - name: Checkout repository
      uses: actions/checkout@v4

    - name: Set up JDK 21
      uses: actions/setup-java@v4
      with:
        java-version: '21'
        distribution: 'temurin'

    # 新增：配置 GitHub 官方的 Gradle 环境
    - name: Setup Gradle
      uses: gradle/actions/setup-gradle@v3

    # 修改：直接使用 gradle 命令，不再依赖 ./gradlew，也删除了 chmod 授权那一步
    - name: Build with Gradle
      run: gradle clean :fakeplayer-dist:shadowJar

    - name: Upload Plugin Artifact
      uses: actions/upload-artifact@v4
      with:
        name: FakePlayer-CE-Fixed-Plugin
        path: fakeplayer-dist/build/libs/FakePlayer-CE-Fixed.jar

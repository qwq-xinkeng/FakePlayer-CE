plugins {
    // 1. 引入真正的 Shadow 打包插件
    id("com.gradleup.shadow") version "8.1.1"
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

dependencies {
    // 2. 将原先的 compileOnly 全部改为 implementation
    // 这样 Shadow 插件才知道要把它们以及它们的底层第三方依赖（如 Guice）打包进去
    implementation(project(":fakeplayer-core"))
    implementation(project(":fakeplayer-api"))
    implementation(project(":fakeplayer-v1_20_1"))
    implementation(project(":fakeplayer-v1_20_2"))
    implementation(project(":fakeplayer-v1_20_3"))
    implementation(project(":fakeplayer-v1_20_4"))
    implementation(project(":fakeplayer-v1_20_5"))
    implementation(project(":fakeplayer-v1_20_6"))
    implementation(project(":fakeplayer-v1_21"))
    implementation(project(":fakeplayer-v1_21_1"))
    implementation(project(":fakeplayer-v1_21_3"))
    implementation(project(":fakeplayer-v1_21_4"))
    implementation(project(":fakeplayer-v1_21_5"))
    implementation(project(":fakeplayer-v1_21_6"))
    implementation(project(":fakeplayer-v1_21_7"))
    implementation(project(":fakeplayer-v1_21_8"))
    implementation(project(":fakeplayer-v1_21_9"))
    implementation(project(":fakeplayer-v1_21_10"))
    implementation(project(":fakeplayer-v1_21_11"))
}

// 3. 配置真正的 shadowJar 任务
tasks.shadowJar {
    archiveFileName.set("FakePlayer-CE-Fixed.jar")
    
    // 排除签名文件以防止 jar 破损报错
    exclude("META-INF/*.SF", "META-INF/*.DSA", "META-INF/*.RSA", "META-INF/*.MF")
}

// 让 build 命令直接触发 shadowJar
tasks.assemble {
    dependsOn(tasks.shadowJar)
}

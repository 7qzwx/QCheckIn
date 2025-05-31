 # QCheckIn 应用

## 应用介绍
QCheckIn是一个签到系统应用。

## 从其他应用启动
QCheckIn应用可以从其他应用启动，支持以下启动方式：


### 使用Intent启动（Kotlin代码）
```kotlin
val intent = Intent().apply {
    action = "qzwx.app.qcheckin.LAUNCH"
}
startActivity(intent)
```


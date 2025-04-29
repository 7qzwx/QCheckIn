 # QCheckIn 应用

## 应用介绍
QCheckIn是一个签到系统应用。

## 从其他应用启动
QCheckIn应用可以从其他应用启动，支持以下启动方式：

### 使用Intent启动（Java代码）
```java
Intent intent = new Intent();
intent.setAction("com.qzwx.qcheckin.LAUNCH");
startActivity(intent);
```

### 使用Intent启动（Kotlin代码）
```kotlin
val intent = Intent().apply {
    action = "com.qzwx.qcheckin.LAUNCH"
}
startActivity(intent)
```

### 使用ADB命令启动（用于测试）
```
adb shell am start -a com.qzwx.qcheckin.LAUNCH
```

注意：启动此应用时不需要传递额外参数。如果应用需要接收参数，请联系开发人员获取更多信息。
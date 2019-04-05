[TOC]


# android layer

首先导包


implementation group: 'com.ashokvarma.android', name: 'bottom-navigation-bar', version: '2.0.4'
    implementation group: 'com.android.support', name: 'design', version: '27.1.1'



开发步骤

1. 创建一个menu文件，3-5 个items
2. 在上下文中布局
3. 设置app:menu 指向menu文件
4. 使用setOnNavigationItemSelectedListener(...)监听选择的事件，


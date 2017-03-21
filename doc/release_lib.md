发布Android Libary 库到 jcenter 网站上：
http://www.open-open.com/lib/view/open1435109824278.html
stetho Android 调试
http://www.jianshu.com/p/03da9f91f41f
http://facebook.github.io/stetho/
https://github.com/facebook/stetho

    
chrome://inspect/ 
在google浏览器中使用这种进行调试



第一步是检查代码的正确性，以及编译library文件（aar，pom等等），输入下面的命令：

> gradlew install
如果没有什么问题，会显示：

BUILD SUCCESSFUL
现在我们已经成功一半了。下一步是上传编译的文件到bintray，使用如下的命令：

gradlew bintrayUpload
如果显示如下你就大喊一声eureka吧！

SUCCESSFUL
在bintray的网页上检查一下你的package。你会发现在版本区域的变化。
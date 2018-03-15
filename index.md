
# 显示意图激活与隐式意图激活

##  Intent意图
作用：激活组件

```markdown

###显示意图激活
**直接指定需要打开的组件类的名称**
-方式一：
-Intent intent = new Intent(this,Target.class);
-startXxx(intent);

-方式二：
-Intent intent = new Intent();
-intent.setClass(this,Target.class);
-startXxx(intent);

-方式三：
-Intent intent = new Intent();
-intent.setClassName(this,"com.xxx.Target");
- 或者intent.setClassName(this, "com.xxx.Target");  
- 或者intent.setClassName(this.getPackageName(), "com.xxx.Target");  
-startXxx(intent);

-方式四：
-Intent intent = new Intent();
-intent.setComponent(new ComponentName(this,Target.class));
- 或者intent.setComponent(new ComponentName(this, "com.xxx.Target"));
- 或者intent.setComponent(new ComponentName(this.getPackageName(), "com.xxx.Target"));  
-startXxx(intent);

**小结**
显式Intent可以直接设置需要开启的组件类，可以唯一确定一个组件，意图明确，所以是显式的。设置这个类的方式可以是Class对象（如Targer.class），
也可以是包名加类名的字符串（如"com.xxx.Target"）。这个很好理解，在android应用程序内部跳转界面常用这种方式。
可通过所有的<intent-filter>来筛选


###隐式意图激活
-隐式，即不像显式意图直接指定需要调用的组件
-隐式不明确指定启动哪个组件，而是通过设置action、data、category属性，让系统来筛选匹配的组件。

-下面以Action为例：
-在清单文件(AndroidManifest.xml)中，首先被调用的Activity有一个<intent-filter>并且包含<action>的Activity
-设定它能处理的Intent，并且category设为"android.intent.category.DEFAULT"。
-action的name是一个字符串，可以自定义，例如我在这里设成"com.xxx.xxx"：
-<activity  
-    android:name="com.xxx.TargetActivity">  
-    <intent-filter>  
-        <action android:name="com.xxx.xxx"/>  
-        <category android:name="android.intent.category.DEFAULT"/>  
-    </intent-filter>  
-</activity>  
-然后，在MainActivity，才可以通过这个action的name找到上面的Activity。
-下面两种方式分别通过setAction和构造方法方法设置Action，两种方式效果相同。

**方式一：setAction方法**
-Intent intent = new Intent();  
-intent.setAction("com.xxx.xxx");  
-startActivity(intent);  

**方式二：构造方法直接设置Action** 
-Intent intent = new Intent("com.xxx.xxx");  
-startActivity(intent); 

-通过设置Action字符串，表明自己的意图，需要由系统解析，找到能够处理这个Intent的Activity并启动。
-比如我想打电话，则可以设置Action为"android.intent.action.DIAL"字符串，表示打电话的意图，系统会找到能处理这个意图的Activity，调出拨号面板。
 
**有几点需要注意** 
-这个Activity其他应用程序也可以调用，只要使用这个Action字符串。这样应用程序之间交互就很容易了，例如手机QQ可以调用QQ空间，可以调用腾讯微博等。 
-因为如此，为了防止应用程序之间互相影响，一般命名方式是包名+Action名，例如这里命名"abcdefg"就很不合理了，就应该改成"com.xxx.xxx.MyTest"。
-当然，你可以在自己的程序中调用其他程序的Action。
  
**例如可以在自己的应用程序中调用拨号面板**
- Intent intent = new Intent(Intent.ACTION_DIAL);  或者Intent intent = new Intent("android.intent.action.DIAL");  
- Intent.ACTION_DIAL是内置常量，值为"android.intent.action.DIAL"  
- startActivity(intent);  
  
 -一个Activity可以处理多种Action只要你的应用够牛逼，一个Activity可以看网页，打电话，发邮件等等。
 -Intent的Action只要是其中之一的一个值，就可以打开这个Activity。
 

-<activity android:name="com.example.app016.SecondActivity">  
-   <intent-filter>  
-       <!-- 可以处理下面三种Intent -->  
-       <action android:name="com.example.app016.SEND_EMAIL"/>  
-       <action android:name="com.example.app016.SEND_MESSAGE"/>  
-       <action android:name="com.example.app016.DAIL"/>  
-       <category android:name="android.intent.category.DEFAULT" />  
-   </intent-filter>  
-</activity>  

##对于一个Action字符串，系统有可能会找到一个Activity能处理这个Action，也有可能找到多个Activity，也可能一个都找不到。

**找到一个Activity**
-很简单，直接打开这个Activity。这个不需要解释。
**找到多个Acyivity**
-系统会提示从多个activity中选择一个打开。
-例如我们自己开发一个拨号面板应用程序，可以设置activity的<intent-filter>中Action的name为"android.intent.action.DIAL"
-这样别的程序调用拨号器时，用户可以从Android自带的拨号器和我们自己开发的拨号器中选择。
-<activity android:name="com.xxx.xxx">  
-    <intent-filter>  
-       <action android:name="android.intent.action.DIAL"/>  
-       <category android:name="android.intent.category.DEFAULT" />  
-    </intent-filter>  
-</activity>  
-这也就是当Android手机装上UC浏览器后，打开网页时会弹出选择Android自带浏览器还是UC浏览器，可能都会遇到过。

**一个Activity都没找到**
-一个都没找到的话，程序就会出错，会抛出ActivityNotFoundException。比如随便写一个action字符串：
-Intent intent = new Intent("xxx");  
-startActivity(intent);  
-所以应该注意try catch异常。
-Intent intent = new Intent("xxx");  
-try  {  
-    startActivity(intent);  
-}  catch(ActivityNotFoundException e)  {  
-    Toast.makeText(this, "找不到对应的Activity", Toast.LENGTH_SHORT).show();  
-} 
-或者也可以使用Intent的resolveActivity方法判断这个Intent是否能找到合适的Activity，如果没有，则不再startActivity，或者可以直接禁用用户操作的控件。
-Intent intent = new Intent(Intent.ACTION_DIAL);  
-if(intent.resolveActivity(getPackageManager()) == null)  {  
-    // 设置控件不可用  
-}  
-注意resolveActivity方法返回值就是显式Intent上面讲到的ComponentName对象，一般情况下也就是系统找到的那个Activity。
-但是如果有多个Activity可供选择的话，则返回的Component是com.android.internal.app.ResolverActivity
-也就是用户选择Activity的那个界面对应的Activity，这里不再深究。
-Intent intent = new Intent(Intent.ACTION_DIAL);  
-ComponentName componentName = intent.resolveActivity(getPackageManager());  
-if(componentName != null) {  
-    String className = componentName.getClassName();  
-    Toast.makeText(this, className, Toast.LENGTH_SHORT).show();  
-}  

1. Numbered
2. List

**Bold** and _Italic_ and `Code` text

[Link](url) and ![Image](src)
```

For more details see [GitHub Flavored Markdown](https://guides.github.com/features/mastering-markdown/).

### Jekyll Themes

Your Pages site will use the layout and styles from the Jekyll theme you have selected in your [repository settings](https://github.com/dcxing111/dcxing111.github.io/settings). The name of this theme is saved in the Jekyll `_config.yml` configuration file.

### Support or Contact

Having trouble with Pages? Check out our [documentation](https://help.github.com/categories/github-pages-basics/) or [contact support](https://github.com/contact) and we’ll help you sort it out.

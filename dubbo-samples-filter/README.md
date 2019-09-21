对于onreturn中配置的通知函数，第一个参数需要为需通知方法的返回值，后面的参数为原来的参数。如果参数不一致的话，则不会生效。
如方法
```java
String sayHi(String name);
```
则对应的通知函数的方法签名应该为
```java
void onReturn(String result, String name);
```
如方法的签名为
```java
String sayHi(String name, String words);
```
则对应的通知函数的方法签名应该为
```java
String onReturn(String result, String name, String words);
```
如若原方法的返回值为`void`的话，同样需要在通知函数中将返回值列出来，否则参数解析的顺序将会出错。
对如下的方法
```java
void sayHello(String name);
```
通知方法的签名形式应该为
```java
String onReturn(Object result, String name)
```
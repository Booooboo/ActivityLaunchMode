# ActivityLaunchMode
A project to test the Activity’s LaunchMode SingleInstance

## SingleInstance

**LaunchMode被设置成SingleInstance的Activity**

1.启动的时候会在单独的task栈中

2.并且一旦被启动只会有这一个实例，如果这个activity没有被destory, 下次再有跳转到该activity的请求，都会调用这个activity的onNewIntent方法，而不是新建实例。

**实验  MainActivity:stand  SingleInstanceActivity: SingleInstance**

MainActivity -> SingleInstanceActivity -> MainActivity ->  SingleInstanceActivity -> MainActivity , 回退-> 回退-> 回退-> 回退

**log打印结果如下:**

08-14 11:44:19.600 21039-21039/? I/bo/MainActivity﹕ ####onCreate task id is 29

08-14 11:44:19.610 21039-21039/? I/bo/MainActivity﹕ ####onResume()

08-14 11:44:32.225 21039-21039/? I/bo/sinstanceactivity﹕ ####onCreate task id is 30

08-14 11:44:32.240 21039-21039/? I/bo/sinstanceactivity﹕ ####onResume()

08-14 11:44:33.985 21039-21039/? I/bo/MainActivity﹕ ####onCreate task id is 29

08-14 11:44:33.995 21039-21039/? I/bo/MainActivity﹕ ####onResume()

08-14 11:44:34.865 21039-21039/? I/bo/sinstanceactivity﹕ ####onNewIntent

08-14 11:44:34.865 21039-21039/? I/bo/sinstanceactivity﹕ ####onResume()

08-14 11:44:35.825 21039-21039/? I/bo/MainActivity﹕ ####onCreate task id is 29

08-14 11:44:35.835 21039-21039/? I/bo/MainActivity﹕ ####onResume()

08-14 11:44:37.270 21039-21039/? I/bo/sinstanceactivity﹕ ####onNewIntent

08-14 11:44:37.270 21039-21039/? I/bo/sinstanceactivity﹕ ####onResume()

08-14 11:44:38.055 21039-21039/? I/bo/MainActivity﹕ ####onCreate task id is 29

08-14 11:44:38.060 21039-21039/? I/bo/MainActivity﹕ ####onResume()

08-14 11:44:39.965 21039-21039/? I/bo/MainActivity﹕ ####onResume()

08-14 11:44:40.350 21039-21039/? I/bo/MainActivity﹕ ####onDestory() task id is 29

08-14 11:44:41.845 21039-21039/? I/bo/MainActivity﹕ ####onResume()

08-14 11:44:42.210 21039-21039/? I/bo/MainActivity﹕ ####onDestory() task id is 29

08-14 11:44:43.045 21039-21039/? I/bo/MainActivity﹕ ####onResume()

08-14 11:44:43.405 21039-21039/? I/bo/MainActivity﹕ ####onDestory() task id is 29

08-14 11:44:45.070 21039-21039/? I/bo/sinstanceactivity﹕ ####onResume()

08-14 11:44:45.460 21039-21039/? I/bo/MainActivity﹕ ####onDestory() task id is 29

08-14 11:44:48.195 21039-21039/? I/bo/sinstanceactivity﹕ ####onDestory() task id is 30



**可见，在回退的时候，先把Mainactivity所在的栈内所有实例回退完，再回退SingInstanceActivity。**

08-14 11:42:32.085 21039-21039/? I/bo/MainActivity﹕ ####onCreate task id is 27

08-14 11:42:32.100 21039-21039/? I/bo/MainActivity﹕ ####onResume()

08-14 11:42:38.900 21039-21039/? I/bo/sinstanceactivity﹕ ####onCreate task id is 28

08-14 11:42:38.910 21039-21039/? I/bo/sinstanceactivity﹕ ####onResume()

08-14 11:42:41.775 21039-21039/? I/bo/MainActivity﹕ ####onCreate task id is 27

08-14 11:42:41.785 21039-21039/? I/bo/MainActivity﹕ ####onResume()

08-14 11:42:43.325 21039-21039/? I/bo/sinstanceactivity﹕ ####onNewIntent

08-14 11:42:43.325 21039-21039/? I/bo/sinstanceactivity﹕ ####onResume()

08-14 11:42:46.695 21039-21039/? I/bo/MainActivity﹕ ####onResume()

08-14 11:42:47.070 21039-21039/? I/bo/sinstanceactivity﹕ ####onDestory() task id is 28

08-14 11:42:52.870 21039-21039/? I/bo/MainActivity﹕ ####onResume()

08-14 11:42:53.240 21039-21039/? I/bo/MainActivity﹕ ####onDestory() task id is 27

08-14 11:42:56.565 21039-21039/? I/bo/MainActivity﹕ ####onDestory() task id is 27

**当然，如果在SingleInstance的栈先回退，SingleInstanceActivity实例最先被销毁。**

## SingleTask

1. 使用singleTask， 如果TaskAffinity相同，则在同一个栈中操作，非栈顶，清空跳转之前的所有activity，然后调用activity的onnewIntent.

2. 使用singleTask， 如果TaskAffinity不相同， 在另一个栈中新建跳转实例，此后的跳转基于新的这个栈。

FLAG_ACTIVITY_NEW_TASK：FLAG_ACTIVITY_NEW_TASK + FLAG_ACTIVITY_CLEAR_TOP标志位组合产生的效果总体上和SingleTask模式相同，除了不会复用FirstActivity实例之外。

如果activity的taskAffinity不同或者还没创建一个与包名同名的taskAffinity,第一次使用 FLAG_ACTIVITY_NEW_TASK跳转到activity,会新建一个task栈(包名同名的taskAffinity或者新定义的taskAffinity名字的task栈),之后的跳转会基于这个栈跳转(此处不同于下面讲的singleInstance)。

示例操作：MainActivity -> singletaskativity -> MainActivity -> singletaskativity -> MainActivity -> singletaskativity -> 回退 -> 回退


08-16 10:36:05.165  12771-12771/com.fragment.app.activitylaunchmode.app I/bo/MainActivity﹕ ####onCreate task id is 6

08-16 10:36:05.180  12771-12771/com.fragment.app.activitylaunchmode.app I/bo/MainActivity﹕ ####onResume()

08-16 10:36:07.600  12771-12771/com.fragment.app.activitylaunchmode.app I/bo/singletaskativity﹕ ####onCreate task id is 7

08-16 10:36:07.610  12771-12771/com.fragment.app.activitylaunchmode.app I/bo/singletaskativity﹕ ####onResume()

08-16 10:36:10.625  12771-12771/com.fragment.app.activitylaunchmode.app I/bo/MainActivity﹕ ####onCreate task id is 7

08-16 10:36:10.635  12771-12771/com.fragment.app.activitylaunchmode.app I/bo/MainActivity﹕ ####onResume()

08-16 10:36:17.835  12771-12771/com.fragment.app.activitylaunchmode.app I/bo/singletaskativity﹕ ####onNewIntent

08-16 10:36:17.835  12771-12771/com.fragment.app.activitylaunchmode.app I/bo/singletaskativity﹕ ####onResume()

08-16 10:36:18.190  12771-12771/com.fragment.app.activitylaunchmode.app I/bo/MainActivity﹕ ####onDestory() task id is 7

08-16 10:36:22.590  12771-12771/com.fragment.app.activitylaunchmode.app I/bo/MainActivity﹕ ####onCreate task id is 7

08-16 10:36:22.600  12771-12771/com.fragment.app.activitylaunchmode.app I/bo/MainActivity﹕ ####onResume()

08-16 10:36:24.850  12771-12771/com.fragment.app.activitylaunchmode.app I/bo/singletaskativity﹕ ####onNewIntent

08-16 10:36:24.850  12771-12771/com.fragment.app.activitylaunchmode.app I/bo/singletaskativity﹕ ####onResume()

08-16 10:36:25.230  12771-12771/com.fragment.app.activitylaunchmode.app I/bo/MainActivity﹕ ####onDestory() task id is 7

08-16 10:45:18.670  12771-12771/com.fragment.app.activitylaunchmode.app I/bo/MainActivity﹕ ####onResume()  // 回退

08-16 10:45:19.050  12771-12771/com.fragment.app.activitylaunchmode.app I/bo/singletaskativity﹕ ####onDestory() task id is 7

08-16 10:45:23.720  12771-12771/com.fragment.app.activitylaunchmode.app I/bo/MainActivity﹕ ####onDestory() task id is 6  // 回退


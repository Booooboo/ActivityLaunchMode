# ActivityLaunchMode
A project to test the Activity’s LaunchMode SingleInstance


##LaunchMode被设置成SingleInstance的Activity 

1.启动的时候会在单独的task栈中

2.并且一旦被启动只会有这一个实例，如果这个activity没有被destory, 下次再有跳转到该activity的请求，都会调用这个activity的onNewIntent方法，而不是新建实例。

##**实验**  MainActivity:stand  SingleInstanceActivity: SingleInstance

MainActivity -> SingleInstanceActivity -> MainActivity ->  SingleInstanceActivity -> MainActivity , 回退-> 回退-> 回退-> 回退

###log打印结果如下：

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



###可见，在回退的时候，先把Mainactivity所在的栈内所有实例回退完，再回退SingInstanceActivity。

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

###当然，如果在SingleInstance的栈先回退，SingleInstanceActivity实例最先被销毁。

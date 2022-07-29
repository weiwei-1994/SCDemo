#import "AppDelegate.h"
#import "HomeController.h"

#import <React/RCTBridge.h>

#import "ReactNativeExceptionHandler.h"
#import "AvoidCrash.h"//捕获异常机制
@interface AppDelegate ()

@end

@implementation AppDelegate

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
  
  [self registAvoidCrash];
  HomeController * home = [[HomeController alloc]init];
  if (@available(iOS 13.0, *)) {
    home.view.backgroundColor = [UIColor systemBackgroundColor];
  } else {
    home.view.backgroundColor = [UIColor whiteColor];
  }
  
  UINavigationController * nav = [[UINavigationController alloc]initWithRootViewController:home];
  self.window = [[UIWindow alloc] initWithFrame:[UIScreen mainScreen].bounds];
  self.window.rootViewController = nav;
  [self.window makeKeyAndVisible];
  
  [self Exception];
  return YES;
}



-(void)Exception{
  [ReactNativeExceptionHandler replaceNativeExceptionHandlerBlock:^(NSException *exception, NSString *readeableException){

      // THE CODE YOU WRITE HERE WILL REPLACE THE EXISTING NATIVE POPUP THAT COMES WITH THIS MODULE.
      //We create an alert box
      UIAlertController* alert = [UIAlertController
                                  alertControllerWithTitle:@"Critical error occurred"
                                  message: [NSString stringWithFormat:@"%@\n%@",
                                            @"Apologies..The app will close now \nPlease restart the app\n",
                                            readeableException]
                                  preferredStyle:UIAlertControllerStyleAlert];

      // We show the alert box using the rootViewController
      [self.window.rootViewController  presentViewController:alert animated:YES completion:nil];

      // THIS IS THE IMPORTANT PART
      // By default when an exception is raised we will show an alert box as per our code.
      // But since our buttons wont work because our click handlers wont work.
      // to close the app or to remove the UI lockup on exception.
      // we need to call this method
      // [ReactNativeExceptionHandler releaseExceptionHold]; // to release the lock and let the app crash.

      // Hence we set a timer of 4 secs and then call the method releaseExceptionHold to quit the app after
      // 4 secs of showing the popup
      [NSTimer scheduledTimerWithTimeInterval:4.0
                                       target:[ReactNativeExceptionHandler class]
                                     selector:@selector(releaseExceptionHold)
                                     userInfo:nil
                                      repeats:NO];

      // or  you can call
      // [ReactNativeExceptionHandler releaseExceptionHold]; when you are done to release the UI lock.
    }];
}

- (void)registAvoidCrash {
    //启动防止崩溃功能(注意区分becomeEffective和makeAllEffective的区别)
    //具体区别请看 AvoidCrash.h中的描述
    //建议在didFinishLaunchingWithOptions最初始位置调用 上面的方法

    [AvoidCrash makeAllEffective];

    //================================================
    //   1、unrecognized selector sent to instance（方式1）
    //================================================


    //若出现unrecognized selector sent to instance导致的崩溃并且控制台输出:
    //-[__NSCFConstantString initWithName:age:height:weight:]: unrecognized selector sent to instance
    //你可以将@"__NSCFConstantString"添加到如下数组中，当然，你也可以将它的父类添加到下面数组中
    //比如，对于部分字符串，继承关系如下
    //__NSCFConstantString --> __NSCFString --> NSMutableString --> NSString
    //你可以将上面四个类随意一个添加到下面的数组中，建议直接填入 NSString

    //我所开发的项目中所防止unrecognized selector sent to instance的类有下面几个，主要是防止后台数据格式错乱导致的崩溃。个人觉得若要防止后台接口数据错乱，用下面的几个类即可。
    //NSStringFromClass(NSString.class);
    NSArray *noneSelClassStrings = @[
                                     @"NSNull",
                                     @"NSNumber",
                                     @"NSString",
                                     @"NSDictionary",
                                     @"NSArray"
                                     ];
    [AvoidCrash setupNoneSelClassStringsArr:noneSelClassStrings];


    //================================================
    //   2、unrecognized selector sent to instance（方式2）
    //================================================

    //若需要防止某个前缀的类的unrecognized selector sent to instance
    //比如你所开发项目中使用的类的前缀:CC、DD
    //你可以调用如下方法
    //NSArray *noneSelClassPrefix = @[
    //                                @"CC",
    //                                @"DD"
    //                                ];
    //[AvoidCrash setupNoneSelClassStringPrefixsArr:noneSelClassPrefix];

    //监听通知:AvoidCrashNotification, 获取AvoidCrash捕获的崩溃日志的详细信息
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(dealwithCrashMessage:) name:AvoidCrashNotification object:nil];
}


/**
 * 监听捕获到的异常
 */
- (void)dealwithCrashMessage:(NSNotification *)note {
    //注意:所有的信息都在userInfo中
    //你可以在这里收集相应的崩溃信息进行相应的处理(比如传到自己服务器)

    //异常拦截并且通过bugly上报
    NSDictionary *info = note.userInfo;
}
@end

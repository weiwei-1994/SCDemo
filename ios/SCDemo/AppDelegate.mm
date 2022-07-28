#import "AppDelegate.h"
#import "HomeController.h"

#import <React/RCTBridge.h>

#import "ReactNativeExceptionHandler.h"
@interface AppDelegate ()

@end

@implementation AppDelegate

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
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
@end

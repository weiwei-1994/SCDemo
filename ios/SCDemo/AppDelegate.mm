#import "AppDelegate.h"
#import "HomeController.h"

#import <React/RCTBridge.h>

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
  return YES;
}

@end

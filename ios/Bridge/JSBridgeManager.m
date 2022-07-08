//
//  JSBridgeManager.m
//  subpackage_test
//
//  Created by yunshan on 2019/3/11.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import "JSBridgeManager.h"

#import "BridgeDelegate.h"


@implementation JSBridgeManager
+(instancetype)shareManager
{
  static JSBridgeManager * manager = nil;
  static dispatch_once_t onceToken;
  dispatch_once(&onceToken, ^{
    manager = [[JSBridgeManager alloc] init];
  });
  return manager;
}

-(void)startWithURL:(NSURL *)URl
{
  self.isHaveLoadDetail = NO;
  
  BridgeDelegate * delegate = [[BridgeDelegate alloc] init];
  delegate.url = URl;
  
  self.bridge = [[RCTBridge alloc] initWithDelegate:delegate launchOptions:nil];

}

+(NSString *)getMainBundlePath{
  NSString *path = [NSString stringWithFormat:@"%@/%@/%@/common.jsbundle",[JSBridgeManager documentsDir],@"Plugins",@"MianBundle"];
  return path;
}
+(NSString *)getPluginPathWithPluginName:(NSString *)name{
  NSString *path = [NSString stringWithFormat:@"%@/%@/%@/%@/business.jsbundle",[JSBridgeManager documentsDir],@"Plugins",@"PluginBundle",name];
  return path;
}


+ (NSString *)documentsDir {
    return [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) firstObject];
}

@end

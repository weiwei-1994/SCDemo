//
//  JSBridgeManager.m
//  subpackage_test
//
//  Created by yunshan on 2019/3/11.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import "JSBridgeManager.h"

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

-(void)start
{
  self.bridge = [[RCTBridge alloc] initWithBundleURL:[[NSBundle mainBundle] URLForResource:@"basic" withExtension:@"jsbundle"] moduleProvider:nil launchOptions:nil];
}

@end

//
//  JSBridgeManager.h
//  subpackage_test
//
//  Created by yunshan on 2019/3/11.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <React/RCTBridge.h>

NS_ASSUME_NONNULL_BEGIN

@interface JSBridgeManager : NSObject
@property (nonatomic, strong) RCTBridge * bridge;
@property (nonatomic, assign) BOOL isHaveLoadDetail;
+(instancetype)shareManager;
-(void)start;
@end

NS_ASSUME_NONNULL_END

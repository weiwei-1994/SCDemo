//
//  RCTBridge+LoadOtherJS.h
//  subpackage_test
//
//  Created by yunshan on 2019/3/11.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import <React/RCTBridge+Private.h>

NS_ASSUME_NONNULL_BEGIN

@interface RCTBridge (LoadOtherJS)
-(void)executeSourceCode:(NSData *)sourceCode sync:(BOOL)sync;
@end

NS_ASSUME_NONNULL_END

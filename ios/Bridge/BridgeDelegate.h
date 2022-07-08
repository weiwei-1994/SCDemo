//
//  BridgeDelegate.h
//  SCDemo
//
//  Created by shenWenFeng on 2022/7/8.
//

#import <Foundation/Foundation.h>
#import <React/RCTBridge.h>

NS_ASSUME_NONNULL_BEGIN


@interface BridgeDelegate : NSObject<RCTBridgeDelegate>

@property (strong, nonatomic)NSURL * url;



@end

NS_ASSUME_NONNULL_END

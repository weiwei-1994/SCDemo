//
//  QRCodeController.h
//  SCDemo
//
//  Created by wyzeww on 2022/7/19.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface ScanCodeResultModel : NSObject

@property(nonatomic,copy)NSString * ip;
@property(nonatomic,copy)NSString * name;

@end

typedef void(^ScanCodeResult)(ScanCodeResultModel * result);

@interface QRCodeController : UIViewController

@property(nonatomic,copy)ScanCodeResult result;

@end

NS_ASSUME_NONNULL_END

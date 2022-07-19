//
//  QRCodeController.m
//  SCDemo
//
//  Created by wyzeww on 2022/7/19.
//

#import "QRCodeController.h"
#import <SGQRCode/SGQRCode.h>
#import <MJExtension/NSObject+MJKeyValue.H>

@implementation ScanCodeResultModel


@end

@interface QRCodeController ()<SGScanCodeDelegate>

@property(nonatomic,strong)SGScanCode * scanCode;
@property(nonatomic,strong)SGScanView * scanView;
@property(nonatomic,strong)UILabel * promptLabel;

@end

@implementation QRCodeController

- (void)dealloc {
    [self stop];
}

- (void)start {
    [self.scanCode startRunning];
    [self.scanView startScanning];
}

- (void)stop {
    [self.scanCode stopRunning];
    [self.scanView stopScanning];
}

- (void)viewDidLoad {
  [super viewDidLoad];
  
  [self loadSubViews];
  [self createQRCode];
    // Do any additional setup after loading the view.
}

-(void)loadSubViews{
  [self.view addSubview:self.scanView];
}

-(void)createQRCode{
  // 创建二维码扫描类
  self.scanCode = [SGScanCode scanCode];
  // 预览视图，必须设置
  self.scanCode.preview = self.view;
  // 遵循 SGScanCodeDelegate
  self.scanCode.delegate = self;
  // 开启扫描
  [self.scanCode startRunning];
}

- (SGScanView *)scanView {
    if (!_scanView) {
        SGScanViewConfigure *configure = [[SGScanViewConfigure alloc] init];
        configure.isShowBorder = YES;
        configure.borderColor = [UIColor clearColor];
        configure.cornerColor = [UIColor whiteColor];
        configure.cornerWidth = 3;
        configure.cornerLength = 15;
        configure.isFromTop = YES;
        configure.scanline = @"SGQRCode.bundle/scan_scanline_qq";
        configure.color = [UIColor clearColor];
        
        CGFloat x = 0;
        CGFloat y = 0;
        CGFloat w = self.view.frame.size.width;
        CGFloat h = self.view.frame.size.height;
        _scanView = [[SGScanView alloc] initWithFrame:CGRectMake(x, y, w, h) configure:configure];
        [_scanView startScanning];
        _scanView.scanFrame = CGRectMake(0, 0, self.view.frame.size.width, self.view.frame.size.height);
    }
    return _scanView;
}

- (UILabel *)promptLabel {
    if (!_promptLabel) {
        _promptLabel = [[UILabel alloc] init];
        _promptLabel.backgroundColor = [UIColor clearColor];
        CGFloat promptLabelX = 0;
        CGFloat promptLabelY = 0.73 * self.view.frame.size.height;
        CGFloat promptLabelW = self.view.frame.size.width;
        CGFloat promptLabelH = 25;
        _promptLabel.frame = CGRectMake(promptLabelX, promptLabelY, promptLabelW, promptLabelH);
        _promptLabel.textAlignment = NSTextAlignmentCenter;
        _promptLabel.font = [UIFont boldSystemFontOfSize:13.0];
        _promptLabel.textColor = [[UIColor whiteColor] colorWithAlphaComponent:0.6];
        _promptLabel.text = @"将二维码/条码放入框内, 即可自动扫描";
    }
    return _promptLabel;
}

- (void)scanCode:(SGScanCode *)scanCode result:(NSString *)result{
  
  ScanCodeResultModel * resultModel = [ScanCodeResultModel mj_objectWithKeyValues:result];
  
  if (self.result) {
    self.result(resultModel);
  }

  [self stop];
  [self dismissViewControllerAnimated:YES completion:nil];
}


/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end

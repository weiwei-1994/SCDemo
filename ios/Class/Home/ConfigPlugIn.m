//
//  ConfigPlugIn.m
//  RaactNativeTest
//
//  Created by shenWenFeng on 2022/6/20.
//

#import "ConfigPlugIn.h"
#import "QRCodeController.h"

@interface ConfigPlugIn ()<UITextFieldDelegate>

@property(nonatomic,strong)NSString * localHost;
@property(nonatomic,strong)NSString * moduleName;
@end

@implementation ConfigPlugIn

- (void)viewDidLoad {
  [super viewDidLoad];
  [self creatUI];
}

-(void)creatUI{
  
  UIButton * navItem = [UIButton buttonWithType:UIButtonTypeCustom];
  [navItem setTitle:@"扫码获取" forState:UIControlStateNormal];
  [navItem setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
  [navItem addTarget:self action:@selector(scanQR) forControlEvents:UIControlEventTouchUpInside];
  self.navigationItem.rightBarButtonItem = [[UIBarButtonItem alloc]initWithCustomView:navItem];
  
  self.view.backgroundColor = [UIColor whiteColor];
  
  UILabel * IPLB = [[UILabel alloc] initWithFrame:CGRectMake(0, 100, 100, 50)];
  IPLB.text = @"电脑IP";
  IPLB.textColor = [UIColor blackColor];
  [self.view addSubview:IPLB];
  
  UITextField * field = [[UITextField alloc] initWithFrame:CGRectMake(CGRectGetMaxX(IPLB.frame), IPLB.frame.origin.y, 200, 50)];
  field.placeholder = @"请输入电脑IP地址";
  field.textColor = [UIColor blackColor];
  field.delegate = self;
  self.localHost = field.text;
  field.tag = 100;
  [self.view addSubview:field];
  
  
  UILabel * nameLB = [[UILabel alloc] initWithFrame:CGRectMake(0, 180, 100, 50)];
  nameLB.text = @"插件名";
  nameLB.textColor = [UIColor blackColor];
  [self.view addSubview:nameLB];
  
  UITextField * field1 = [[UITextField alloc] initWithFrame:CGRectMake(CGRectGetMaxX(nameLB.frame), nameLB.frame.origin.y, 200, 50)];
  field1.placeholder = @"请输入插件名";
  field1.textColor = [UIColor blackColor];
  field1.delegate = self;
  field1.tag = 101;
  [self.view addSubview:field1];
  
  
  
  UIButton * OKBtn = [[UIButton alloc] initWithFrame:CGRectMake(20, CGRectGetMaxY(nameLB.frame), 100, 50)];
  OKBtn.backgroundColor= [UIColor greenColor];
  
  [OKBtn addTarget:self action:@selector(clickOKBtn) forControlEvents:UIControlEventTouchUpInside];
  [OKBtn setTitle:@"确定" forState:UIControlStateNormal];
  [self.view addSubview:OKBtn];

}

-(void)scanQR{
  
  __weak typeof(self) weakSelf = self;

  QRCodeController * QRCodeVC = [[QRCodeController alloc]init];
  QRCodeVC.result = ^(ScanCodeResultModel * _Nonnull result) {
    
    UITextField * ipTextField = (UITextField *)[weakSelf.view viewWithTag:100];
    ipTextField.text = [NSString stringWithFormat:@"%@:8081",result.ip];
    weakSelf.localHost = ipTextField.text;
    
  
    UITextField * nameTextField = (UITextField *)[weakSelf.view viewWithTag:101];
    nameTextField.text = result.name;
    weakSelf.moduleName = nameTextField.text;
    
  };
  [self presentViewController:QRCodeVC animated:YES completion:nil];
}

- (void)textFieldDidChangeSelection:(UITextField *)textField{
  if (textField.tag == 100) {
    self.localHost = textField.text;
  }else{
    self.moduleName = textField.text;
  }
}



-(void)clickOKBtn{
  
  if (self.blcok && self.localHost && self.moduleName) {
    self.blcok(self.localHost, self.moduleName);
    [self.navigationController popViewControllerAnimated:YES];
  }
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

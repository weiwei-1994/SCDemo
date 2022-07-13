//
//  HomeController.m
//  RaactNativeTest
//
//  Created by wyzeww on 2022/5/18.
//

#import "HomeController.h"
#import "ReactNativeController.h"
#import <React/RCTRootView.h>
#import <AFNetworking/AFNetworking.h>
#import "ConfigPlugIn.h"
#import "JSBridgeManager.h"
#import "RCTBridge+LoadOtherJS.h"
#import "PluginModel.h"
@interface HomeController ()<UITableViewDataSource,UITableViewDelegate>

@property(nonatomic,strong)NSArray * dataSource;
@property(nonatomic,strong)UITableView * tableView;


@property(nonatomic,strong)NSString * localHost;
@property(nonatomic,strong)NSString * moduleName;

//0 调试 1 bundle 2沙河
@property(nonatomic,assign)NSInteger OpenType;

@property(nonatomic,strong)UIButton* leftBt;


@property(nonatomic,strong)NSURL * commonUrl;



@end

@implementation HomeController

- (void)viewDidLoad {
  [super viewDidLoad];
  
  [self addNotification];
  self.title = @"APP首页";
  [self.navigationController.navigationBar setTitleTextAttributes:@{NSFontAttributeName:[UIFont systemFontOfSize:17],NSForegroundColorAttributeName:[UIColor blackColor]}];
  
  UIButton * rightBt = [[UIButton alloc] initWithFrame:CGRectMake(0, 0, 50, 50)];
  [rightBt setTitle:@"设置" forState:UIControlStateNormal];
  [rightBt setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
  [rightBt addTarget:self action:@selector(gotoSetting) forControlEvents:UIControlEventTouchUpInside];
  self.navigationItem.rightBarButtonItem = [[UIBarButtonItem alloc] initWithCustomView:rightBt];
  
  UIButton * leftBt = [[UIButton alloc] initWithFrame:CGRectMake(0, 0, 150, 50)];
  [leftBt setTitle:@"服务调试模式" forState:UIControlStateNormal];
  [leftBt setTitleColor:[UIColor blackColor] forState:UIControlStateNormal];
  [leftBt addTarget:self action:@selector(clickMode) forControlEvents:UIControlEventTouchUpInside];
  self.leftBt = leftBt;
  self.navigationItem.leftBarButtonItem = [[UIBarButtonItem alloc] initWithCustomView:leftBt];
  
  [self updateSource];
  [self.view addSubview:self.tableView];
  
  // Do any additional setup after loading the view.
}


-(void)addNotification{
  [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(JavaScriptDidLoad) name:RCTJavaScriptDidLoadNotification object:nil];
}

- (void)JavaScriptDidLoad
{
  
}

-(void)updateSource{
  
  if (self.OpenType == 0) {
    
    PluginModel * model0 = [[PluginModel alloc] init];
    model0.moduleName = @"调试APP";
    self.dataSource = @[model0];
    
  }else if(self.OpenType == 1){
    PluginModel * model0 = [[PluginModel alloc] init];
    model0.moduleName = @"SCDemo";
    model0.filePath = [[NSBundle mainBundle] URLForResource:@"business" withExtension:@"jsbundle"].path;
    
    PluginModel * model1 = [[PluginModel alloc] init];
    model1.moduleName = @"SPluginOne";
    model1.filePath = [[NSBundle mainBundle] URLForResource:@"SPluginOne_business" withExtension:@"jsbundle"].path;
    
    PluginModel * model2 = [[PluginModel alloc] init];
    model2.moduleName = @"SPluginTwo";
    model2.filePath = [[NSBundle mainBundle] URLForResource:@"SPluginTwo_business" withExtension:@"jsbundle"].path;
    
    self.dataSource = @[model0,model1,model2];
    
  }else if(self.OpenType == 2){
    PluginModel * model0 = [[PluginModel alloc] init];
    model0.moduleName = @"SCDemo";
    model0.filePath =  [JSBridgeManager getPluginPathWithPluginName:@"SCDemo"];
    
    self.dataSource = @[model0];
    
  }
  
}

//   点击选择模式
-(void)clickMode{
  UIAlertController *alertController = [UIAlertController alertControllerWithTitle:@"请选择加载资源" message:nil preferredStyle:UIAlertControllerStyleActionSheet];
  __weak typeof(self)weakSelf = self;
  [alertController addAction:[UIAlertAction actionWithTitle:@"服务调试模式" style:UIAlertActionStyleDefault handler:^(UIAlertAction * _Nonnull action) {
    weakSelf.OpenType = 0;
    [weakSelf.leftBt setTitle:@"服务调试模式" forState:UIControlStateNormal];
    [weakSelf updateSource];
    [weakSelf.tableView reloadData];
  }]];
  [alertController addAction:[UIAlertAction actionWithTitle:@"本地bundle资源" style:UIAlertActionStyleDefault handler:^(UIAlertAction * _Nonnull action) {
    
    weakSelf.OpenType = 1;
    [[JSBridgeManager shareManager] startWithURL:[[NSBundle mainBundle] URLForResource:@"common" withExtension:@"jsbundle"]];
    [weakSelf.leftBt setTitle:@"本地bundle资源" forState:UIControlStateNormal];
    
    [weakSelf updateSource];
    [weakSelf.tableView reloadData];
    
  }]];
  [alertController addAction:[UIAlertAction actionWithTitle:@"本地沙河资源" style:UIAlertActionStyleDefault handler:^(UIAlertAction * _Nonnull action) {
    
    weakSelf.OpenType = 2;
    NSString * mainPath = [JSBridgeManager getMainBundlePath];
    if ([weakSelf isExistsAtPath:mainPath]) {
      [[JSBridgeManager shareManager] startWithURL:[NSURL URLWithString:mainPath]];
    }
    [weakSelf.leftBt setTitle:@"本地沙河资源" forState:UIControlStateNormal];
    [weakSelf updateSource];
    [weakSelf.tableView reloadData];
    
  }]];
  
  [alertController addAction:[UIAlertAction actionWithTitle:@"取消" style:UIAlertActionStyleDestructive handler:^(UIAlertAction * _Nonnull action) {
    
    NSLog(@"取消");
    
  }]];
  
  [self presentViewController:alertController animated:YES completion:nil];
  
}

//设置调试模式的参数
-(void)gotoSetting{
  ConfigPlugIn * PlugInSet = [[ConfigPlugIn alloc] init];
  __weak typeof(self) weakSelf = self;
  [PlugInSet setBlcok:^(NSString * _Nonnull localHost, NSString * _Nonnull moduleName) {
    weakSelf.localHost = localHost;
    weakSelf.moduleName = moduleName;
    
  }];
  [self.navigationController pushViewController:PlugInSet animated:YES];
}

-(UITableView *)tableView{
  if (_tableView == nil) {
    _tableView = [[UITableView alloc]initWithFrame:CGRectMake(0, 80, [UIScreen mainScreen].bounds.size.width, [UIScreen mainScreen].bounds.size.height)];
    _tableView.backgroundColor = [UIColor whiteColor];
    _tableView.delegate = self;
    _tableView.dataSource = self;
    
  }
  return _tableView;
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section{
  
  return self.dataSource.count;
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath{
  UITableViewCell * cell = [tableView dequeueReusableCellWithIdentifier:@"cellId"];
  if (cell == nil) {
    cell = [[UITableViewCell alloc]initWithStyle:UITableViewCellStyleDefault reuseIdentifier:@"cellId"];
  }
  
  PluginModel *model = self.dataSource[indexPath.row];
  
  cell.textLabel.text = model.moduleName;
  return cell;
}

-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath{
  [tableView deselectRowAtIndexPath:indexPath animated:YES];
  //  NSString * operation = self.dataSource[indexPath.row];
  //  if ([operation isEqualToString:@"下载插件包"]) {
  //    [self downloadPluginWithPluginId:@"12345"];
  //  }else{
  [self openPluginWithIndex:indexPath.row];
  //  }
  
}

-(void)openPluginWithIndex:(NSInteger) index{
  
  ReactNativeController * reactNavtiveVC = [[ReactNativeController alloc]init];
  
  RCTRootView * rootView;
  if (self.OpenType == 0) {
    rootView = [self loadWithURL];
  }else if(self.OpenType == 1){
    rootView = [self loadDetailBundleWithModel:self.dataSource[index]];
  }else if(self.OpenType == 2){
    rootView = [self loadDocumentWithModel:self.dataSource[index]];
  }
  if (!rootView) {
    return;
  }
  reactNavtiveVC.view = rootView;
  [self.navigationController pushViewController:reactNavtiveVC animated:YES];
}

//拼接包
-(RCTRootView *)loadDetailBundleWithModel:(PluginModel *)model
{
//  NSError *error = nil;
  //获取detail Bundle文件
//  NSData * detailBundleData = [NSData dataWithContentsOfFile:model.filePath
//                                                     options:NSDataReadingMappedIfSafe
//                                                       error:&error];
//  if (!error && ![JSBridgeManager shareManager].isHaveLoadDetail) {
    //加载eDetailbundle
//    [[JSBridgeManager shareManager].bridge.batchedBridge executeSourceCode:detailBundleData sync:NO];
    
    [[JSBridgeManager shareManager].bridge loadAndExecuteSplitBundleURL:[NSURL URLWithString:model.filePath] onError:^(NSError *error) {
    } onComplete:^{
      NSLog(@"加载分包完成");
    }];
//    [JSBridgeManager shareManager].isHaveLoadDetail = YES;
//  }
  RCTRootView *rootView = [[RCTRootView alloc] initWithBridge:[JSBridgeManager shareManager].bridge moduleName:model.moduleName initialProperties:nil];
  return rootView;
}


//沙河拼接包
-(RCTRootView *)loadDocumentWithModel:(PluginModel *)model{
  
  
  
  
  NSString * path = model.filePath;
  
  if (![self isExistsAtPath:path]) {
    return nil;
  }
  NSLog(@"分包路径%@",path);
  
//  NSError *error = nil;
//  //获取detail Bundle文件
//  NSData * detailBundleData = [NSData dataWithContentsOfFile:path
//                                                     options:NSDataReadingMappedIfSafe
//                                                       error:&error];
//  if (!error && ![JSBridgeManager shareManager].isHaveLoadDetail) {
    //加载eDetailbundle
//    [[JSBridgeManager shareManager].bridge.batchedBridge executeSourceCode:detailBundleData sync:NO];
    [[JSBridgeManager shareManager].bridge loadAndExecuteSplitBundleURL:[NSURL URLWithString:model.filePath] onError:^(NSError *error) {
    } onComplete:^{
      NSLog(@"加载分包完成");
    }];
//    [JSBridgeManager shareManager].isHaveLoadDetail = YES;
//  }
  RCTRootView *rootView = [[RCTRootView alloc] initWithBridge:[JSBridgeManager shareManager].bridge moduleName:model.moduleName initialProperties:nil];
  return rootView;
  
}

//加载全量包，通过路径
-(RCTRootView *)loadWithURL{
  
  if (!self.localHost || !self.moduleName) {
    return nil;
  }
  
  NSURL *jsCodeLocation;
#if DEBUG
  NSString * url = [NSString stringWithFormat:@"http://%@:8081/index.bundle?platform=ios",self.localHost];
  
  jsCodeLocation = [NSURL URLWithString:url];
#else
  //    jsCodeLocation = [NSURL URLWithString:[self getPluginPathWithPluginId:@"12345"]];
  
  jsCodeLocation = [NSURL URLWithString:[[NSBundle mainBundle] pathForResource:@"index" ofType:@"jsbundle"]];
#endif
  //RNDemo RNHighScores
  RCTRootView *rootView = [[RCTRootView alloc] initWithBundleURL: jsCodeLocation
                                                      moduleName: self.moduleName
                                               initialProperties: nil
                                                   launchOptions: nil];
  return rootView;
  
}



-(void)downloadPluginWithPluginId:(NSString *)pluginId{
  if (![self isExistsAtPath:[self getPluginsPath]]) {
    [self createDirectoryAtPath:[self getPluginsPath] error:nil];
  }
  
  if ([self isExistsAtPath:[self getPluginPathWithPluginId:pluginId]]) {
    [self removeItemAtPath:[self getPluginPathWithPluginId:pluginId] error:nil];
  }
  
  //  [self downLoadPlugin:pluginId];
}

- (NSString *)documentsDir {
  return [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) firstObject];
}

- (BOOL)isExistsAtPath:(NSString *)path {
  return [[NSFileManager defaultManager] fileExistsAtPath:path];
}

- (BOOL)createDirectoryAtPath:(NSString *)path error:(NSError *__autoreleasing *)error {
  NSFileManager *manager = [NSFileManager defaultManager];
  /* createDirectoryAtPath:withIntermediateDirectories:attributes:error:
   * 参数1：创建的文件夹的路径
   * 参数2：是否创建媒介的布尔值，一般为YES
   * 参数3: 属性，没有就置为nil
   * 参数4: 错误信息
   */
  BOOL isSuccess = [manager createDirectoryAtPath:path withIntermediateDirectories:YES attributes:nil error:error];
  return isSuccess;
}

- (BOOL)removeItemAtPath:(NSString *)path error:(NSError *__autoreleasing *)error {
  return [[NSFileManager defaultManager] removeItemAtPath:path error:error];
}

-(void)downLoadPlugin:(NSString *)pluginId{
  //下载插件包
  NSURLSessionConfiguration *configuration = [NSURLSessionConfiguration defaultSessionConfiguration];
  
  AFURLSessionManager *manager = [[AFURLSessionManager alloc] initWithSessionConfiguration:configuration];
  NSURL *URL = [NSURL URLWithString:@"https://venus-redis-backup.s3.us-west-2.amazonaws.com/index.bundle"];
  NSURLRequest *request = [NSURLRequest requestWithURL:URL];
  
  NSURLSessionDownloadTask *downloadTask = [manager downloadTaskWithRequest:request progress:nil destination:^NSURL *(NSURL *targetPath, NSURLResponse *response) {
    NSString * path = [self getPluginPathWithPluginId:pluginId];
    NSURL *url = [NSURL fileURLWithPath:path];
    return url;
  } completionHandler:^(NSURLResponse *response, NSURL *filePath, NSError *error) {
  }];
  [downloadTask resume];
}

-(NSString *)getPluginsPath{
  return [NSString stringWithFormat:@"%@/%@",[self documentsDir],@"Plugins"];
}

-(NSString *)getPluginPathWithPluginId:(NSString *)pluginId{
  return [[self getPluginsPath]stringByAppendingPathComponent:[NSString stringWithFormat:@"%@/index.bundle",pluginId]];
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

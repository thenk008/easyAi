# easyAi
本包说明：
* 本包原名imageMarket,因为开始加入自然语言模块，所以之后更名为easyAi
* 本包对物体在图像中进行训练及识别，切割，定位的轻量级，面向小白的框架,功能在逐渐扩展中
* 本包对中文输入语句，对输入语句的类别进行分类，功能在逐渐扩展中
* 若有想扩充的功能请进群提意见，若是通用场景我会陆续补充，技术交流群：561433236
## 详细视频教程地址：
* 视频教程地址：https://www.bilibili.com/video/av89134035
## 测试素材下载
链接:https://pan.baidu.com/s/1Vzwn3iMPBI-FAXBDrCSglg  
密码:7juj
## 框架效果演示结果:
* 因为是框架没有图像化界面，演示结果就是控制台输出的数据，只能用视频展示，想看演示结果请看教学视频
## 控制台输出演示
* 演示都是输出结果，详情请看具体视频教程，上有链接，这里就是展示一下控制台输出截图。
* 单物体识别效果：[点击查看](./src/test/image/end1.png)
* 多物体识别效果：[点击查看](./src/test/image/end2.png)
* 中文语言分类效果：[点击查看](./src/test/image/end3.png)
### 目前拥有的功能是
* 对单张图片单物体进行识别
* 对单张图片多物体进行识别与定位
* 对中文语言进行分类语义识别,判断用户说话的语义是什么，要做什么
* 若有想扩充的功能请进群提意见，若是通用场景我会陆续补充，技术交流群：561433236
### 目的是
* 低硬件成本，CPU可快速学习运行，面向jAVA开发的程序员，经过简单API调用就可实现物体在图像中的识别，定位及中文语言分类等功能
* 努力为中小企业提供AI场景解决技术方案
* 技术交流群：561433236
### 特点是
入手门槛低，简单配置，快速上手
#### 为什么做这个包
* 低门槛化：
现在随着人工智能技术的兴起，很多场景需要开发人员添加相应的功能，但是在二三线城市算法人才匮乏。
并且大多是JAVA开发程序员，业务做的更多，因为作者本人就是三线城市程序员，所以深知这一点。
所以我本人认为需要一款部署简单，不需要学习任何算法知识，
只通过最简单的API调用，就可以实现部分人工智能应用，并面向覆盖面最广的JAVA程序员使用的，且
能满足大部分AI业务场景实现的技术包。
* 面向用户：广大没接触过算法知识，人才相对匮乏的二三线JAVA业务开发程序员，实现人工智能应用
* 部署简单：
本包所有底层函数及数学库都是作者JAVA手写，不依赖任何第三方库，所以开发者只需要将本包下载到本地后，打成JAR包
引入到自己的POM文件中，就可以独立使用所有功能。
* 功能还在扩展：
本包现在的功能还在逐步扩展中
* 抛错捕获暂时还没有做全，若有抛错请进群交流：561433236，我来做一下错误定位
## HELLO WORLD说明：
* 以下为最简API文档，所有非必设参数都使用本引擎默认值
* 要注意的是使用最简API，及参数默认值准确度远不能达到最佳状态
### 图像学习部分最简API 说明:
``` java
 //创建图片解析类
 Picture picture = new Picture();
 //创建一个静态单例配置模板
 static TempleConfig templeConfig = new TempleConfig();
 //第三个参数和第四个参数分别是训练图片的宽和高，为保证训练的稳定性请保证训练图片大小的一致性
 templeConfig.init(StudyPattern.Accuracy_Pattern, true, 640, 640, 2);
 //将配置模板类作为构造塞入计算类
 Operation operation = new Operation(templeConfig);
 //一阶段 循环读取不同的图片
 for (int i = 1; i < 1900; i++) {
 //读取本地URL地址图片,并转化成矩阵
 Matrix a = picture.getImageMatrixByLocal("/Users/lidapeng/Desktop/myDocment/picture/a" + i + ".jpg");
 Matrix c = picture.getImageMatrixByLocal("/Users/lidapeng/Desktop/myDocment/picture/c" + i + ".jpg");
 //矩阵塞入运算类进行学习，第一个参数是图片矩阵，第二个参数是图片分类标注ID，第三个参数是第一次学习固定false
 operation.learning(a, 1, false);
 operation.learning(c, 2, false);
 }
 for (int i = 1; i < 1900; i++) {
 //读取本地URL地址图片,并转化成矩阵
 Matrix a = picture.getImageMatrixByLocal("D:\\share\\picture/a" + i + ".jpg");
 Matrix c = picture.getImageMatrixByLocal("D:\\share\\picture/c" + i + ".jpg");
 //将图像矩阵和标注加入进行学习，Accuracy_Pattern 模式 进行第二次学习
 //第二次学习的时候，第三个参数必须是 true
 operation.learning(a, 1, true);
 operation.learning(c, 2, true);
 }
 templeConfig.finishStudy();//结束学习
 //获取学习结束的模型参数,并将model保存数据库
 ModelParameter modelParameter = templeConfig.getModel();
 String model = JSON.toJSONString(modelParameter);
```
### 单物体图像识别部分最简API 说明：
``` java
   //读取一张图片，并将其转化为矩阵
   Matrix a = picture.getImageMatrixByLocal(fileURL);
   //返回此图片的分类ID
   int an = operation.toSee(a);
```
### 物体图像识别服务启动初始化API 说明:
``` java
 //创建一个静态单例配置模板类
 static TempleConfig templeConfig = new TempleConfig();
 //初始化配置模板
 templeConfig.init(StudyPattern.Accuracy_Pattern, true, 640, 640, 2);
 //将配置模板类作为构造塞入计算类
 Operation operation = new Operation(templeConfig);
 //从数据库中读取学习的模型结果，反序列为ModelParameter       
 ModelParameter modelParameter = JSON.parseObject(ModelData.DATA2, ModelParameter.class);
 //将模型数据注入配置模板类   
 templeConfig.insertModel(modelParameter);
```
### 自然语言分类最简API 说明:
``` java
        //创建模板读取类
        TemplateReader templateReader = new TemplateReader();
        //读取语言模版，第一个参数是模版地址，第二个参数是文本编码方式
        //同时也是学习过程
        templateReader.read("/Users/lidapeng/Desktop/myDocment/a1.txt", "UTF-8");
        //学习结束获取模型参数
        //WordModel wordModel = WordTemple.get().getModel();
        //不用学习注入模型参数
        //WordTemple.get().insertModel(wordModel);
        Talk talk = new Talk();
        //输入语句进行识别，若有标点符号会形成LIST中的每个元素
        //返回的集合中每个值代表了输入语句，每个标点符号前语句的分类
        List<Integer> list = talk.talk("帮我配把锁");
        System.out.println(list);
        //这里做一个特别说明，语义分类的分类id不要使用"0",本框架约定如果类别返回数字0，则意味不能理解该语义，即分类失败
        //通常原因是模板量不足，或者用户说的话的语义，不在你的语义分类训练范围内
   ```
### 神经网络最简API说明
``` java
     //创建一个DNN神经网络管理器
     NerveManager nerveManager = new NerveManager(...);
     //构造参数
     //sensoryNerveNub 感知神经元数量 即输入特征数量
     //hiddenNerverNub  每一层隐层神经元的数量
     //outNerveNub 输出神经元数量 即分类的类别
     //hiddenDepth 隐层神经元深度，即学习深度
     //activeFunction 激活函数
     //isDynamic 是否启用动态神经元数量(没有特殊需求建议为静态，动态需要专业知识)
     public NerveManager(int sensoryNerveNub, int hiddenNerverNub, int outNerveNub, int hiddenDepth, ActiveFunction activeFunction, boolean isDynamic)
     nerveManager.getSensoryNerves()获取感知神经元集合
     //eventId:事件ID
     //parameter:输入特征值
     //isStudy:是否是学习
     //E:特征标注
     //OutBack 回调类
     SensoryNerv.postMessage(long eventId, double parameter, boolean isStudy, Map<Integer, Double> E, OutBack outBack)
     //每一次输出结果都会返回给回调类，通过回调类拿取输出结果，并通过eventId来对应事件
```
### 随机森林最简API说明
``` java
        //创建一个内存中的数据表
        DataTable dataTable = new DataTable(column);
        //构造参数是列名集合
        public DataTable(Set<String> key)
        //指定主列名集合中该表的主键
        dataTable.setKey("point");
        //创建一片随机森林
        RandomForest randomForest = new RandomForest(7);
        //构造参数为森林里的树木数量
        public RandomForest(int treeNub)
        //唤醒随机森林里的树木
        randomForest.init(dataTable);
        //将加入数据的实体类一条条插入森林中
        randomForest.insert(Object object);
        //森林进行学习
        randomForest.study();
        //插入特征数据，森林对该数据的最终分类结果进行判断
        randomForest.forest(Object objcet);
```
### 如何提升精准度
*  将模板类设置为不忽略精度
``` java
 TempleConfig templeConfig = new TempleConfig(false, true);
```
>使用TempleConfig()有参构造，第一个参数目前依然固定传false(因为有功能还没完成)，第二个参数是一个布尔值是否忽略精度。  
众所周知JAVA计算浮点是有精度损失的，如果选择true则使用大数计算类即无精度损失（默认为FALSE），但是这是以速度变慢十倍以上为代价的。  
忽略精度和使用精度准确度上差距，平均在5-6个百分点之间，所以用户按照自己的需求来判断是否使用精度计算。
>>优点：准确度提升5-6个百分点，配合DNN分类器可达99%以上的准确率
>>>缺点：运算速度变慢十倍以上，从百毫秒变为一至两秒
* 根据业务情景选择使用分类器
``` java
 TempleConfig templeConfig = new TempleConfig(false, true);
 templeConfig.setClassifier(Classifier.DNN);
```
>在TempleConfig类调用init()方法前选择使用的分类器setClassifier()
``` java
public class Classifier {//分类器
    public static final int LVQ = 1;//LVQ分类
    public static final int DNN = 2; //使用DNN分类
    public static final int VAvg = 3;//使用特征向量均值分类
}
```
>目前easyAi提供三种分类器,即用户选择一种参数设置进配置模板类 
>> 这三种分类器的特点
>> 1. LVQ:若用户训练图片量较少，比如一个种类只有一两百张图片，则使用此分类器达到当前条件下最大识别成功率
>> 2. VAvg:若用户训练图片较少，同时分类数量也很少，比如只训练两三种物体识别，则VAvg达到当前最大识别成功率
>> 3. DNN:若用户训练图片较多，每种分类1500张训练图片，则使用此分类器，准确率98%+。
>> 4. 若不设置分类器，则框架默认使用VAvg分类器
>>>使用DNN的话，API略有区别
``` java
 //创建图片解析类
 Picture picture = new Picture();
 //创建一个静态单例配置模板
 static TempleConfig templeConfig = new TempleConfig();
 //使用DNN分类器
 templeConfig.setClassifier(Classifier.DNN);
 //第三个参数和第四个参数分别是训练图片的宽和高，为保证训练的稳定性请保证训练图片大小的一致性
 templeConfig.init(StudyPattern.Accuracy_Pattern, true, 640, 640, 2);
 //将配置模板类作为构造塞入计算类
 Operation operation = new Operation(templeConfig);
 //一阶段 循环读取不同的图片
 for (int i = 1; i < 1900; i++) {
 //读取本地URL地址图片,并转化成矩阵
 Matrix a = picture.getImageMatrixByLocal("/Users/lidapeng/Desktop/myDocment/picture/a" + i + ".jpg");
 Matrix c = picture.getImageMatrixByLocal("/Users/lidapeng/Desktop/myDocment/picture/c" + i + ".jpg");
 //矩阵塞入运算类进行学习，第一个参数是图片矩阵，第二个参数是图片分类标注ID，第三个参数是第一次学习固定false
 operation.learning(a, 1, false);
 operation.learning(c, 2, false);
 }
 for (int i = 1; i < 1900; i++) {
 Matrix a = picture.getImageMatrixByLocal("D:\\share\\picture/a" + i + ".jpg");
 Matrix c = picture.getImageMatrixByLocal("D:\\share\\picture/c" + i + ".jpg");
 operation.normalization(a, templeConfig.getConvolutionNerveManager());
 operation.normalization(c, templeConfig.getConvolutionNerveManager());
 }
 templeConfig.getNormalization().avg();
 for (int i = 1; i < 1900; i++) {
 //读取本地URL地址图片,并转化成矩阵
 Matrix a = picture.getImageMatrixByLocal("D:\\share\\picture/a" + i + ".jpg");
 Matrix c = picture.getImageMatrixByLocal("D:\\share\\picture/c" + i + ".jpg");
 //将图像矩阵和标注加入进行学习，Accuracy_Pattern 模式 进行第二次学习
 //第二次学习的时候，第三个参数必须是 true
 operation.learning(a, 1, true);
 operation.learning(c, 2, true);
 }
```
>大家可以看到如果使用DNN,在一阶段学习和二阶段学习之间多了一段代码即：
``` java
for (int i = 1; i < 1900; i++) {
 Matrix a = picture.getImageMatrixByLocal("D:\\share\\picture/a" + i + ".jpg");
 Matrix c = picture.getImageMatrixByLocal("D:\\share\\picture/c" + i + ".jpg");
 operation.normalization(a, templeConfig.getConvolutionNerveManager());
 operation.normalization(c, templeConfig.getConvolutionNerveManager());
 }
 templeConfig.getNormalization().avg();
```
* 增加DNN神经网络深度
``` java
templeConfig.setDeep(int deep);
```
>若不设置默认深度为2，设置的深度越深则意味着准确率也越高，但同时也意味着你需要更多的训练图片去训练  
>增加一层深度，训练图片的数量至少乘以3，否则准确度不仅不会增加，反而会下降
>>优点：更深的深度准确率可以无限接近100%，我们只要训练量足够大，我们就可以更深，越深越无敌
>>>缺点：增加深度意味着成几何倍数提升的训练量，同时也意味着过拟合的风险
* 修改可调参数
``` java
        //选择分类器
        templeConfig.setClassifier(Classifier.DNN);
        //选择期望矩阵宽度
        templeConfig.setMatrixWidth(5);
        //选择正则化模式
        templeConfig.setRzType(RZ.L2);
        //选择DNN 深度
        templeConfig.setDeep(1);
        //设置学习率
        templeConfig.setStudyPoint(0.05);
        //设置DNN隐层宽度
        templeConfig.setHiddenNerveNub(6);
        //设置正则系数
        templeConfig.setlParam(0.015);//0.015
```
### 什么样的图片会严重影响准确率
* 训练图片或者识别图片有，比较大片的遮盖，暗光，阴影，或者使图片模糊的情况，识别和训练都会有严重的干扰
* 训练图片要求会比较高，要求若物体无背景则扣干净，有背景的话使用统一背景不要变动，不要一个训练一个物体有很多种背景
* 训练物体图片若有背景最好垫张白纸，纯白和纯色的背景干扰度最小，纯白最优
* 识别图片的拍摄设备最好有补光灯，不要让拍摄物体有大片阴暗。除非你同时也训练阴暗图片，当然那样的话训练量太大不推荐，也没必要
* 若使用定位与多物体识别功能，请将训练物体的图片背景扣干净,或保证RGB纯白色号背景即0XFFFFFF
* 某两种物体的图片太过相似即长的几乎一模一样,区别点特别小或者微弱,识别不出来，就好比同卵双胞胎你也很难做出区分
### 常见抛错
* error:Wrong size setting of image in templateConfig
* 原因是模版配置类图片宽高设置相差太大
` templeConfig.init(StudyPattern.Accuracy_Pattern, true, width, height, 1);`
##### 最终说明
* TempleConfig()：配置模版类，一定要静态在内存中长期持有，检测的时候不要每次都NEW，
一直就使用一个配置类就可以了。
* Operation():运算类，除了学习可以使用一个以外，用户每检测一次都要NEW一次。
因为学习是单线程无所谓，而检测是多线程，如果使用一个运算类，可能会造成线程安全问题
##### 精准模式和速度模式的优劣
* 速度模式学习很快，但是检测速度慢，双核i3检测单张图片（1200万像素）单物体检测速度约800ms.
学习1200万像素的照片物体，1000张需耗时1-2小时。
* 精准模式学习很慢，但是检测速度快，双核i3检测单张图片（1200万像素）单物体检测速度约100ms.
学习1200万像素的照片物体，1000张需耗时5-7个小时。
##### 本包为性能优化而对AI算法的修改
* 本包的自然语言是通过内置分词器进行语句分词，然后再通过不同分词的时序进行编号成离散特征，最后进入随机森林分类
* 本包对图像AI算法进行了修改，为应对CPU部署。
* 卷积层不再使用权重做最终输出，而是将特征矩阵作出明显分层的结果。
* 卷积神经网络后的全连接层直接替换成了LVQ算法进行特征向量量化学习聚类，通过卷积结果与LVQ原型向量欧式距离来进行判定。
* 物体的边框检测通过卷积后的特征向量进行多元线性回归获得，检测边框的候选区并没有使用图像分割（cpu对图像分割算法真是超慢），
而是通过Frame类让用户自定义先验图框大小和先验图框每次移动的检测步长，然后再通过多次检测的IOU来确定是否为同一物体。
* 所以添加定位模式，用户要确定Frame的大小和步长，来替代基于图像分割的候选区推荐算法。
* 速度模式是使用固定的边缘算子进行多次卷积核，然后使用BP的多层神经网络进行强行拟合给出的结果（它之所以学习快，就是因为速度模式学习的是
全连接层的权重及阈值，而没有对卷积核进行学习）
* 本包检测使用的是灰度单通道，即对RGB进行降纬变成灰度图像来进行检测（RGB三通道都算的话，CPU有些吃不住）。
* 若使用本包还有疑问可自行看测试包内的HelloWorld测试案例类，或者进群交流：561433236,提出问题

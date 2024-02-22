<div align="center">

# Coze-Discord-Bridge

_免费的ChatGPT Turo 128k API_

_通过 `discord bot`调用 `coze 托管 discord bot`实现`免费使用GPT-4作为API`_

_觉得有点用的话 别忘了点个🌟_

</div>

## 警告

由于 Coze 对发送者添加了是否为bot检测

虽目前已经修复

但不保证未来 Coze 不会通过更多方式阻止bot调用

因此作者不保证该项目的稳定性

## 截图
![image](https://github.com/catx-feitu/Coze-Discord-Bridge/assets/108512490/24e4304b-a5f7-4baa-9559-8c01f9a935b3)

![image](https://github.com/catx-feitu/Coze-Discord-Bridge/assets/108512490/285d33e5-6898-4324-8f9c-8842f3a3912c)

## 功能

注:是最新源代码里支持的功能 不是Release里的 要看Release的往前翻Commit api文档 配置文件同样
- [X] 适配`NextChat`,`LobeChat`等可以修改OpenAPI URL的AI平台
- [X] HTTP/HTTPS API支持
- [X] 支持文生图(需`coze`配置`DALL·E3`/`DALL·E2`插件)返回图片url
- [X] 支持图生文(需`coze`配置`GPT4V`插件)(发送的文本消息中携带图片url/自己上传base64图片)
- [x] 支持对话隔离
- [X] 对话支持流式返回
- [X] 支持和`openai`对齐的对话接口(`v1/chat/completions`)
- [X] 支持和`openai`对齐的图像生成接口(`v1/images/generations`)
- [X] 突破Discord Bot 2k字消息长度上限
- [X] 定时活跃机器人 自定义活跃间隔 避免bot因为太久未互动而离线
- [ ] 导入此jar进行二次开发 [::80%]
- [ ] WebUI
- [ ] 多个Bot 负载均衡
- [ ] Token计数

大饼很甜,苦了的只是猫猫

## 部署准备材料

1.一个Windows/Linux/...机器 (只要能运行java,能联网就行)  需要安装java (推荐jdk17 已知jdk8及以下版本不兼容)

2.一个代理服务器/材料一的机器在国外

3.一个手机号/Google账号

4.一个Discord账号

## 部署

1.下载Release或者自行构建

2.运行一遍 `java -jar CozeDiscordBridge-xxxxxx.jar` 如果一切正常,你可以在运行目录看到  `Config.yml` 配置文件

3.打开配置文件,进行编辑

````
#Github: https://github.com/catx-feitu/coze-discord-bridge
Bots:
  -  #访问密钥 留空或default 表示无需密钥 通过不同的密钥链接不同的bot
     Key: "default"
     #Discord bot token 获取方法
     #浏览器打开 https://discord.com/developers/
     #创建Application > 点击Bot > 点击 Reset Token 然后复制过来即可
     #注意 还需要打开Privileged Gateway Intents下面的选项 (MESSAGE CONTENT INTENT一定要开)
     Discord_Bot_Token: ""
     #创建频道时使用的父频道 (也可以理解成 分组) 打开开发者模式 右键就可以看到ID 为空关闭
     CreateChannel_Category: ""
     #Coze Bot所处的服务器ID 打开Discord开发者模式 右键服务器复制过来即可
     Server_id: ""
     #接入Coze的Bot id 邀请进服务器在用户列表右键 复制用户ID 过来即可
     CozeBot_id: ""

#配置是否启用代理  代理类型 HTTP 或 SOCKS 常用于中国大陆机器部署
UsingProxy: false
ProxyIP: 127.0.0.1
ProxyPort: 8080
ProxyType: HTTP

#API端口设置为0关闭 如果HTTP和HTTPS都监听失败则无法启动
#API端口 默认8092 curl http://127.0.0.1:8092/Ping
APIPort: 8092
#API HTTPS 端口 默认8093 curl https://127.0.0.1:8093/Ping
APISSLPort: 8093
......
````
首先你要在[Discord开发者平台](https://discord.com/developers/)创建两个Application

![image](https://github.com/catx-feitu/Coze-Discord-Bridge/assets/108512490/41310da4-5db7-46df-946d-de642b64f985)

点击Bot 然后获取Token 复制保存到其它地方

![image](https://github.com/catx-feitu/Coze-Discord-Bridge/assets/108512490/bf45bbd4-0039-4723-b781-38854b607bbc)

往下滑动 开启下面三个按钮 随后点击保存

![image](https://github.com/catx-feitu/Coze-Discord-Bridge/assets/108512490/dd4a278d-9bac-45b4-b871-f3c92a136172)

点击Oauth 勾选Bot 然后往下滑勾选Administrator (省事 如果注重安全性那么请确保 链接到Coze的bot能收发和编辑消息 链接到Coze-Discord-Birdge的bot能收发消息和创建删除子频道)

复制下方生成的URL

![image](https://github.com/catx-feitu/Coze-Discord-Bridge/assets/108512490/8bb919a9-0dd5-480e-8062-2733b5ef5084)
之后第二个Bot相同的操作 这样你就有了两个Token 两个URL

打开Discord App(网页版亦可) 创建一个服务器

然后依次打开两个URL 把两个bot都添加进服务器

![image](https://github.com/catx-feitu/Coze-Discord-Bridge/assets/108512490/1c72bf00-dfc7-48fe-adb8-fcfcbf05ad95)

点击左下角设置打开Discord设置

![image](https://github.com/catx-feitu/Coze-Discord-Bridge/assets/108512490/2310f3c6-1060-4b18-a026-0feaaf1a82e0)



之后登录[Coze AI Studio](https://www.coze.com/)创建一个Bot
![image](https://github.com/catx-feitu/Coze-Discord-Bridge/assets/108512490/9de286bf-d6bd-43a4-a2a0-8566dd706d84)

之后可以配置Bot 添加插件(要能AI画图必须添加) 调整GPT设置之类的(Dialog round = 对话轮数  推荐拉满) 最后点击右上角Publish

![image](https://github.com/catx-feitu/Coze-Discord-Bridge/assets/108512490/cf33dfef-33f6-420c-8473-aecddb789432)

输入 Token 点击保存 然后Publish

ps:Changelog必填 随便写即可 如果你有强迫症的话那..不太建议..

![image](https://github.com/catx-feitu/Coze-Discord-Bridge/assets/108512490/f5b32f9e-8f9b-484a-afcd-7a935904dd45)

如果配置正确你应该能看到一个机器人在线 一个机器人离线

![image](https://github.com/catx-feitu/Coze-Discord-Bridge/assets/108512490/580a331d-713f-4686-961e-8c3169bcbee4)

下滑 找到高级设置 开启开发者模式

![image](https://github.com/catx-feitu/Coze-Discord-Bridge/assets/108512490/ca33a63f-6de7-44d5-b6bb-b13162712056)

点击左上角复制服务器ID

![image](https://github.com/catx-feitu/Coze-Discord-Bridge/assets/108512490/d84f4363-a0f6-4f05-abc8-798f5742794a)

点击右侧复制Bot 用户ID

![image](https://github.com/catx-feitu/Coze-Discord-Bridge/assets/108512490/9b560037-9d4d-4324-892d-dbb8bcc90bc3)

在Config中保存这两个ID


![image](https://github.com/catx-feitu/Coze-Discord-Bridge/assets/108512490/a0b28f98-616a-425b-8cde-a677f4e691d3)

最后保存另一个没有使用的Bot Token进去

![image](https://github.com/catx-feitu/Coze-Discord-Bridge/assets/108512490/fac6a998-c32b-4a39-aef3-f8a00b1ab65f)

4.再次运行 `java -jar CozeDiscordBridge-xxxxxx.jar` 如下显示则正常  如果您是使用的是Windows且控制台编码为GBK 请先执行`chcp 65001`

![image](https://github.com/catx-feitu/Coze-Discord-Bridge/assets/108512490/803bfe60-39d5-42d5-b1b3-7aaf932a2808)

ps:第一次启动报错 `读取 cache_names.json 失败` 正常 直接忽略即可

5.最后可通过curl或者其它工具测试 如果服务器内亮机器人互问互答就是部署成功啦

![image](https://github.com/catx-feitu/Coze-Discord-Bridge/assets/108512490/a87d04ac-5c52-4929-bb7c-ff62bd2fde65)

## (可选)keepalive

因为Discord/Coze问题 当bot很长时间不互动会离线 遇到这种情况需要去Coze手动重新登录 很麻烦

因此 你可以通过编辑配置开启keepalive功能

它可以自动与Coze托管的bot对话 当累计到一段时间bot没有被互动过

````
......
# Keepalive 通过定时与Coze托管的bot互动防止因为太久未发言而被强制下限
# 内置定时器执行周期 单位分钟  设置 0 关闭  关闭后也可以通过api调用keepalive
Keepalive_timer: 0
# 只有大于指定分钟未发言Coze托管的bot才执行keepalive 单位分钟
Keepalive_maxIntervalMinutes: 720
# keepalive发送消息所在频道
Keepalive_sendChannel: "keepalive"
# keepalive发送消息内容
Keepalive_sendMessage: "keepalive"
````

当关闭内置定时器时 你也可以通过访问终结点`/api/keepalive`来执行keepalive任务

## API文档

调用方式: GET `http://地址:端口/终结点?key1=value1&key2=value2`  POST `http://地址:端口/终结点` `{"key1":"value1","key2":"value2"}`

终结点无需区分大小写

<br>

终结点:`/` 根目录

参数:无

返回:Int code 状态码 固定200 | String message 信息 固定内容 | data {Int now 当前时间 10位时间戳} 数据

<br>

终结点:`/Ping` 检查服务是否活着

参数:无

返回:Int code 状态码 固定200 | String message 信息 固定Pong!

<br>

终结点:`/api/CreateConversation` 创建对话

参数:<可选>String name 创建的子频道名称 也可以用于之后调用(如果没开启 `Disable_Name_Cache` 的话)

返回:Int code 状态码 200为成功 | String message 信息 额外说明 | data {String conversation_id 频道ID 即使开启 `Disable_Name_Cache` 也能用作日后调用对话,String conversation_name 频道名称 如果参数 `name` 为空则随机生成} 数据

<br>

终结点:`/api/Chat` 聊天

参数:String name 名称 | String prompt 提示词 | <可选>String image 图片(经过base64编码过的png图片数据)

返回:Int code 状态码 200为成功 | String message 信息 额外说明 | data {String prompt bot生成的文本,String[] files bot生成/找到的图片} 数据

<br>

终结点:`/api/ChatStream` 聊天

参数:String name 名称 | String prompt 提示词 | <可选>String image 图片(经过base64编码过的png图片数据)

返回:

中途:Int code 状态码 200为成功 | String message 信息 固定为`生成中` | data {String prompt_all bot生成的文本,String prompt_new bot新生成的文本,String[] files bot生成/找到的图片,Boolean done 是否完成 固定false} 数据

完成:Int code 状态码 200为成功 | String message 信息 | data {String prompt_all bot生成的文本,String prompt_new bot新生成的文本 为空,String[] files bot生成/找到的图片,Boolean done 是否完成 固定true} 数据

`````
curl --no-buffer "http://127.0.0.1:8092/api/ChatStream?name=1201576967368085686&prompt=1"
data: {"code":200,"data":{"done":false,"files":[],"prompt_all":"Ah, I see you've signaled me with a \"1\". How","prompt_new":"Ah, I see you've signaled me with a \"1\". How"},"message":"生成中.."}

data: {"code":200,"data":{"done":false,"files":[],"prompt_all":"Ah, I see you've signaled me with a \"1\". How can","prompt_new":" can"},"message":"生成中.."}

data: {"code":200,"data":{"done":false,"files":[],"prompt_all":"Ah, I see you've signaled me with a \"1\". How can I assist","prompt_new":" I assist"},"message":"生成中.."}

data: {"code":200,"data":{"done":false,"files":[],"prompt_all":"Ah, I see you've signaled me with a \"1\". How can I assist you","prompt_new":" you"},"message":"生成中.."}

data: {"code":200,"data":{"done":false,"files":[],"prompt_all":"Ah, I see you've signaled me with a \"1\". How can I assist you today? If you have any questions or there's something you'd like to share, please go ahead","prompt_new":" today? If you have any questions or there's something you'd like to share, please go ahead"},"message":"生成中.."}

data:{"code":200,"data":{"done":false,"files":[],"prompt_all":"Ah, I see you've signaled me with a \"1\". How can I assist you today? If you have any questions or there's something you'd like to share, please go ahead!","prompt_new":"!"},"message":"生成中.."}

data: {"code":200,"data":{"done":true,"files":[],"prompt_all":"Ah, I see you've signaled me with a \"1\". How can I assist you today? If you have any questions or there's something you'd like to share, please go ahead!","prompt_new":""},"message":"成功!"}
`````

<br>

终结点:`/api/GetLatestMessage` 获取上一次对话消息(常用于聊天中途连接丢失获取消息内容)

参数:String name 名称

返回:Int code 状态码 200为成功 | String message 信息 额外说明 | data {Boolean status 是否找到,String prompt bot生成的文本,String[] files bot生成/找到的图片} 数据

<br>

终结点:`/api/ConversationIsFound` 对话是否存在

参数:String name 名称

返回:Int code 状态码 200为成功 | String message 信息 额外说明 | data {Boolean status 是否存在,String conversation_id 频道ID 即使开启 `Disable_Name_Cache` 也能用作日后调用对话,String conversation_name 频道名称} 数据

<br>

终结点:`/api/DeleteConversation` 删除对话

参数:String name 名称

返回:Int code 状态码 200为成功 | String message 信息 额外说明 | data {Boolean status 是否成功} 数据

<br>

终结点:`/api/RenameConversation` 对话改名   如果你开启 `Disable_Name_Cache` 那么只能改Discord子频道名称

参数:String name 名称 | String new_name 新名称

返回:Int code 状态码 200为成功 | String message 信息 额外说明 | data {Boolean status 是否成功,String conversation_id 频道ID,String conversation_name 频道名称} 数据

<br>

终结点:`/api/keepalive` 执行keepalive任务

参数:无

返回:Int code 状态码 200为成功 | String message 信息 额外说明

<br>


终结点:`/v1/xxxx` OpenAI官方接口适配

仅适配了部分 具体请参照[OpenAI官方文档](https://platform.openai.com/docs/introduction)

## Api-key

确保安全性 您还可以通过配置文件开启安全访问

之后你需要在header或者请求参数中添加`key`属性才能访问

````
D:\GITHUB\coze-discord-bridge\build\libs>curl --get --data-urlencode "prompt=___启动" "http://127.0.0.1:8092/api/Chat?name=xxxxxxxxxxx
{"code":403,"message":"无权访问本服务"}
D:\GITHUB\coze-discord-bridge\build\libs>curl --get --data-urlencode "prompt=___启动" "http://127.0.0.1:8092/api/Chat?name=xxxxxxxxxxx&key=nya_
{"code":200,"data":{"files":[],"prompt":"哦，明白啦！如果你是在寻求帮助来启动《___》游戏，......"},"message":"成功!"}
````

## 结尾

本项目不推荐用于商业用途

如果使用本项目即同意使用本项目所造成的一切后果(不限于Coze,Discord账号被封禁)作者不承担任何责任

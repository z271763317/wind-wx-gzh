wind-wx-gzh是一款封装了【微信公众号】服务器推送来的消息处理（如：粉丝用户关注【含二维码】、粉丝用户取消关注【含二维码】、菜单点击等），开发者只需要实现相应的接口即可（开发者需要在微信平台后台配置服务器URL为：【你的服务】/receive。如：http://wx.tcin.cn/receive）

开发者只需要关注（实现）以下包的接口：

事件推送：org.wind.wx.gzh.service.message.event

通消息：org.wind.wx.gzh.service.message.standard

//app.js
App({
  onLaunch: function () {
    // 展示本地存储能力
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    // 登录
    wx.login({
      success: res => {
        console.log("jscode:" + res.code) //这里只是为了在微信小程序客户端好查看结果，找寻问题
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
          wx.request({
            method: "GET",
            url: this.globalData.apiHost +'/todos/data/api/login', 
            data: {
              jscode: res.code // 使用wx.login得到的登陆凭证，用于换取openid
            },
            header: {
              'content-type': 'application/json' // 默认值
            },
            success: (res) => {
              console.log("sessionid:" + res.data.result.sessionid);
              this.globalData.sessionid = res.data.result.sessionid;
            }
          })
          
      }
    })

    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {
              // 可以将 res 发送给后台解码出 unionId
              this.globalData.userInfo = res.userInfo

              // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
              // 所以此处加入 callback 以防止这种情况
              if (this.userInfoReadyCallback) {
                this.userInfoReadyCallback(res)
              }
            }
          })
        }
      }
    })
  },
  globalData: {
    sessionid : null,
    userInfo: null,
    imageHost: "https://file.dogiant.cn",
    apiHost: "https://www.dogiant.cn"
  }
})
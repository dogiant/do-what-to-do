// pages/welcome/welcome.js
const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    inThePlan:true,
    bannerUrl: "resources/pic/1.jpg"
  },
  todayReadTask : function(e){
    wx.navigateTo({
      url: '/pages/content/content'
    })
  },
  createReadTask: function (e) {
    wx.navigateTo({
      url: '/pages/plan/index'
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var self = this;
    wx.request({
      url: 'https://www.dogiant.cn/todos/data/api/getDailyBanner', 
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        console.log(res.data)
        var bannerUrl = app.globalData.imageHost + res.data.result.imageUrl;
        self.setData({ bannerUrl : bannerUrl})
      }
    });
    
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  }
})
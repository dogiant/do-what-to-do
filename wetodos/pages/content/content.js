// pages/content/content.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    article:{
      title:"赠汪伦",
      dynasty:"唐",
      author:"李白",
      photo:"/images/poem/zengwanglun.png",
      content: "李白乘舟将欲行，忽闻岸上踏歌声。\r\n桃花潭水深千尺，不及汪伦送我情。"
    }
  
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    console.log(options);
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
  
  },
  showQuestions: function(){
    wx.navigateTo({
      url: '/pages/question/question',
    })
  }
})
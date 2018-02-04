// pages/task/task.js
const util = require('../../utils/util.js')
Page({

  /**
   * 页面的初始数据
   */
  data: {
    startDate:null,
    endDate:null,
    selectedDate:null,
    books:[{
      name:"成语故事大全",
      days:100,
      imageUrl:"https://img3.doubanio.com/lpic/s29658310.jpg"
    }, {
      name: "成语故事大全",
      days: 100,
      imageUrl: "https://img3.doubanio.com/lpic/s29658310.jpg"
    }]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    const date = new Date();
    const startDate = util.formatDate(date);
    console.log(startDate)
    const endDate = util.formatDate(date);
    console.log(endDate)
    this.setData({
      startDate : startDate,
      endDate : endDate,
      selectedDate: startDate
    })
  },
  bindDateChange : function(event){
    console.log(event);
    this.setData({
      selectedDate : event.detail.value
    })
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
exports.install = function (Vue, options) {
  Vue.prototype.isEmptyObject = function (object){//全局函数1
    for (i in object) {
      return false
    }
    return true
  };
};

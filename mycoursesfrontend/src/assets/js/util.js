exports.install = function (Vue, options) {
  Vue.prototype.isEmptyObject = function (object) {//全局函数1
    for (i in object) {
      return false
    }
    return true
  };
  Vue.prototype.getPath = function (serverPath) {
    let index = serverPath.indexOf("static") + 6;
    return serverPath.slice(index);
  };
  Vue.prototype.goBack = function () {
    this.$router.go(-1);
  };
};

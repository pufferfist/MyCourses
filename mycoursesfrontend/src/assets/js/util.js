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
  Vue.prototype.processCSV = function (file) {
    let result=[];
    let list = file.split(/\r?\n|\r/);
    for (let i = 1; i < list.length; i++) {
      if (list[i] === "")
        return result;
      let pair=list[i].split(",");
      result.push({
        id:pair[0],
        grade:pair[1]
      })
    }
    return result;
  };
};

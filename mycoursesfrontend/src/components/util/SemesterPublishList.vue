<template>
  <div>
    <semester-publish-item v-for="publishItem in publishList"
                           :key="publishItem.id"
                           v-bind:publish="publishItem"></semester-publish-item>
  </div>
</template>

<script>
  import SemesterPublishItem from "./SemesterPublishItem";

  export default {
    name: "SemesterPublishList",
    components: {SemesterPublishItem},
    props: ["publishList"]
  }
</script>

<style scoped>

</style>

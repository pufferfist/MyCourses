<template>
  <div>
    <publish-item v-for="publishItem in publishList"
                  :key="publishItem.id"
                  v-bind:publish="publishItem"></publish-item>
  </div>
</template>

<script>
  import PublishItem from "./PublishItem";

  export default {
    name: "PublishList",
    components: {PublishItem},
    props: ["publishList"]
  }
</script>

<style scoped>

</style>

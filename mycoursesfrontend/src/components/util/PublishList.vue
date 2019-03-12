<template>
  <div>
    <publish-item  v-if="this.type==='standard'"
                   v-for="publishItem in publishList"
                   :key="publishItem.id"
                   v-bind:publish="publishItem"></publish-item>
    <publish-item  v-if="this.type==='semester'"
                   v-for="publishItem in publishList"
                   :key="publishItem.id"
                   v-bind:publish="publishItem"></publish-item>
  </div>
</template>

<script>
    import PublishItem from "./PublishItem";
    export default {
        name: "PublishList",
      components: {PublishItem},
      props:["publishList","type"]
    }
</script>

<style scoped>

</style>

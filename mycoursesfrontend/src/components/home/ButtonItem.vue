<template>
  <div class="mt3 center">
    <router-link to="{{path}}">
      <el-button type="primary" round>
        <slot></slot>
      </el-button>
    </router-link>
  </div>
</template>

<script>
  export default {
    name: "ButtonItem",
    props: ["path"]
  }
</script>

<style scoped>

</style>

<template>
  <div class="mt3 center">
      <el-button type="primary" round v-on:click="$emit('click')">
        <slot></slot>
      </el-button>
  </div>
</template>

<script>
    export default {
        name: "ButtonItem"
    }
</script>

<style scoped>

</style>

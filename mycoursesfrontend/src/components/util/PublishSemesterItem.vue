<template>
  <div class="pt2 pb2 ba-ns mb3">
    <a v-on:click="transfer">{{publish.semester}}</a>
  </div>
</template>

<script>
  export default {
    name: "PublishSemesterItem",
    props: ["publish"],
    methods:{
      transfer(){
        this.$store.commit('set',{type:"publish",data:this.publish});
        this.$router.push('/'+this.$store.state.type+'/publishInfo')
      }
    }
  }
</script>

<style scoped>

</style>

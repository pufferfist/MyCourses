<template>
  <div class="pt2 pb2 border mb3 f2">
    <a v-on:click="transfer">{{publish.semester}}{{publish.classNumber}}班</a>
  </div>
</template>

<script>
  export default {
    name: "SemesterPublishItem",
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
  .border{
    border-radius: 3px;
    border: 1px solid;
    border-color: rgba(138,138,138,0.32);
  }
</style>

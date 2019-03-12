<template>
  <div class="pt2 pb2 border mb3 f2">
    <a v-on:click="transfer">{{course.name}}</a>
  </div>
</template>

<script>
  export default {
    name: "CourseItem",
    props: ["course"],
    methods:{
      transfer(){
        this.$store.commit('set',{type:"course",data:this.course});
        this.$router.push('/'+this.$store.state.type+'/courseInfo')
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
